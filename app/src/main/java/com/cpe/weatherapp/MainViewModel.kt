package com.cpe.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cpe.weatherapp.database.WeatherInfoDao
import com.cpe.weatherapp.database.toWeatherInfo
import com.cpe.weatherapp.database.toWeatherInfoEntity
import com.cpe.weatherapp.ui.models.WeatherInfo
import com.cpe.weatherapp.ui.models.toWeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

private const val REQUEST_ENABLE_BT = 1
private const val ERROR_READ = 0

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val dao: WeatherInfoDao,
) : AndroidViewModel(application) {

    private val tag = "MainActivity"

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                ERROR_READ -> {
                    val arduinoMsg = msg.obj.toString() // Read message from Arduino
                    readings = arduinoMsg
                }
            }
        }
    }

    //Intances of BT Manager and BT Adapter needed to work with BT in Android.
    private val bluetoothManager: BluetoothManager =
        application.getSystemService(BluetoothManager::class.java)
    private val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter

    lateinit var arduinoBTModule: BluetoothDevice
    private var arduinoUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    //Using a handler to update the interface in case of an error connecting to the BT device
    //My idea is to show handler vs RxAndroid

    // Create an Observable from RxAndroid
    //The code will be executed when an Observer subscribes to the the Observable
    val connectToBTObservable = Observable.create<String?> { emitter: ObservableEmitter<String?> ->
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

    private var requestBluetoothPermission = {}
    private var startEnableBluetoothActivity = {}

    @OptIn(ExperimentalCoroutinesApi::class)
    val weatherHistory = dao.getAll().mapLatest { weatherHistory ->
        weatherHistory.map { it.toWeatherInfo() }
    }

    var canConnect by mutableStateOf(false)
        private set

    var devices by mutableStateOf("")
        private set

    var readings by mutableStateOf("")
        private set

    var weatherInfo by mutableStateOf<WeatherInfo?>(null)
        private set

    var showHistory by mutableStateOf(false)
        private set

    var isReloading = false

    fun init(
        requestBluetoothPermission: () -> Unit,
        startEnableBluetoothActivity: () -> Unit,
    ) {
        this.requestBluetoothPermission = requestBluetoothPermission
        this.startEnableBluetoothActivity = startEnableBluetoothActivity

        reload()

        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                reload()
                mainHandler.postDelayed(this, 2_000)
            }
        })
    }

    fun toggleShowHistory() {
        showHistory = !showHistory
    }

    private fun reload() {
        if (!isReloading) {
            isReloading = true
            if (readings == "Unable to connect to the BT device" || readings == "") {
                searchDevices()
            }
            refreshReadings()
            saveWeatherInfo()
        }
    }

    private fun searchDevices() {
        canConnect = false

        //Check if the phone supports BT
        Log.d(tag, "Device support Bluetooth")
        //Check BT enabled. If disabled, we ask the user to enable BT
        if (!bluetoothAdapter.isEnabled) {
            Log.d(tag, "Bluetooth is disabled")
//            val enableBtIntent =
//                Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            if (ActivityCompat.checkSelfPermission(
                    application.applicationContext,
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
                requestBluetoothPermission()
                Log.d(tag, "We don't BT Permissions")
            } else {
                Log.d(tag, "We have BT Permissions")
            }
            startEnableBluetoothActivity()
//            startActivityForResult(
//                enableBtIntent,
//                REQUEST_ENABLE_BT
//            )
            Log.d(tag, "Bluetooth is enabled now")
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
            }
    }

    private fun saveWeatherInfo() {
        weatherInfo?.let { weatherInfo ->
            viewModelScope.launch(Dispatchers.IO) {
                dao.insert(weatherInfo.toWeatherInfoEntity())
            }
        }
    }

}