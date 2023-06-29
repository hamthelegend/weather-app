package com.cpe.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.cpe.weatherapp.models.WeatherInfo
import com.cpe.weatherapp.models.toWeatherInfo
import com.cpe.weatherapp.ui.screen.main.ConnectionState
import com.cpe.weatherapp.ui.screen.main.MainScreen
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.UUID

private const val REQUEST_ENABLE_BT = 1
private const val ERROR_READ = 0

@Suppress("CallToThreadRun")
class MainActivity : ComponentActivity() {

    val tag = "MainActivity"
    lateinit var handler: Handler
    lateinit var arduinoBTModule: BluetoothDevice
    var arduinoUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    lateinit var bluetoothManager: BluetoothManager
    lateinit var bluetoothAdapter: BluetoothAdapter
    lateinit var connectToBTObservable: Observable<String?>

    var canConnect by mutableStateOf(false)

    var devices by mutableStateOf("")
    var readings by mutableStateOf("")

    var weatherInfo by mutableStateOf<WeatherInfo?>(null)

    var isReloading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Intances of BT Manager and BT Adapter needed to work with BT in Android.
        bluetoothManager = getSystemService(BluetoothManager::class.java)
        bluetoothAdapter = bluetoothManager.adapter

        //Using a handler to update the interface in case of an error connecting to the BT device
        //My idea is to show handler vs RxAndroid

        //Using a handler to update the interface in case of an error connecting to the BT device
        //My idea is to show handler vs RxAndroid
        handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    ERROR_READ -> {
                        val arduinoMsg = msg.obj.toString() // Read message from Arduino
                        readings = arduinoMsg
                    }
                }
            }
        }

        // Create an Observable from RxAndroid
        //The code will be executed when an Observer subscribes to the the Observable
        connectToBTObservable = Observable.create<String?> { emitter: ObservableEmitter<String?> ->
            Log.d(tag, "Calling connectThread class")
            //Call the constructor of the ConnectThread class
            //Passing the Arguments: an Object that represents the BT device,
            // the UUID and then the handler to update the UI
            val connectThread =
                ConnectThread(arduinoBTModule, arduinoUUID, handler)
            connectThread.run()
            //Check if Socket connected
            if (connectThread.mmSocket.isConnected) {
                Log.d(tag, "Calling ConnectedThread class")
                //The pass the Open socket as arguments to call the constructor of ConnectedThread
                val connectedThread = ConnectedThread(connectThread.mmSocket)
                connectedThread.run()
                if (connectedThread.valueRead != null) {
                    // If we have read a value from the Arduino
                    // we call the onNext() function
                    //This value will be observed by the observer
                    emitter.onNext(connectedThread.valueRead)
                }
                //We just want to stream 1 value, so we close the BT stream
                connectedThread.cancel()
            }
            // SystemClock.sleep(5000); // simulate delay
            //Then we close the socket connection
            connectThread.cancel()

            isReloading = false

            //We could Override the onComplete function
            emitter.onComplete()
        }

        reload()

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                reload()
                mainHandler.postDelayed(this, 2_000)
            }
        })

        setContent {
            val connectionState = when (readings) {
                "Unable to connect to the BT device", "" -> ConnectionState.Disconnected
                else -> ConnectionState.Connected
            }

            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column {
                        MainScreen(
                            weatherInfo = weatherInfo,
                            connectionState = connectionState,
                            onConnectClick = ::reload,
                        )
//                        DebugScreen()
                    }
                }
            }
        }
    }

    private fun reload() {
        if (!isReloading) {
            isReloading = true
            searchDevices()
            refreshReadings()
        }
    }

    @Composable
    private fun DebugScreen() {
        Button(onClick = ::searchDevices) {
            Text("Search")
        }
        Text(text = devices)
        Button(enabled = canConnect, onClick = ::refreshReadings) {
            Text(text = "Connect")
        }
        Text(text = readings)
    }

    private fun searchDevices() {
        canConnect = false

        //Check if the phone supports BT
        Log.d(tag, "Device support Bluetooth")
        //Check BT enabled. If disabled, we ask the user to enable BT
        if (!bluetoothAdapter.isEnabled) {
            Log.d(tag, "Bluetooth is disabled")
            val enableBtIntent =
                Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Log.d(tag, "We don't BT Permissions")
                startActivityForResult(
                    enableBtIntent,
                    REQUEST_ENABLE_BT
                )
                Log.d(tag, "Bluetooth is enabled now")
            } else {
                Log.d(tag, "We have BT Permissions")
                startActivityForResult(
                    enableBtIntent,
                    REQUEST_ENABLE_BT
                )
                Log.d(tag, "Bluetooth is enabled now")
            }
        } else {
            Log.d(tag, "Bluetooth is enabled")
        }
        var btDevicesString = ""
        val pairedDevices = bluetoothAdapter.bondedDevices
        if (pairedDevices.size > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (device in pairedDevices) {
                val deviceName = device.name
                val deviceHardwareAddress =
                    device.address // MAC address
                Log.d(tag, "deviceName:$deviceName")
                Log.d(
                    tag,
                    "deviceHardwareAddress:$deviceHardwareAddress"
                )
                //We append all devices to a String that we will display in the UI
                btDevicesString =
                    "$btDevicesString$deviceName || $deviceHardwareAddress\n"
                //If we find the HC 05 device (the Arduino BT module)
                //We assign the device value to the Global variable BluetoothDevice
                //We enable the button "Connect to HC 05 device"
                if (deviceName == "HC-05") {
                    Log.d(tag, "HC-05 found")
                    arduinoUUID = device.uuids[0].uuid
                    arduinoBTModule = device
                    //HC -05 Found, enabling the button to read results
                    canConnect = true
                }
                devices = btDevicesString
            }
        }
        Log.d(tag, "Button Pressed")
    }

    @SuppressLint("CheckResult")
    fun refreshReadings() {
        connectToBTObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { valueRead: String? ->
                //valueRead returned by the onNext() from the Observable
                readings = valueRead ?: ""
                weatherInfo = readings.toWeatherInfo() ?: weatherInfo
                isReloading = false
            }
    }
}