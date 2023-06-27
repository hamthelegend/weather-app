package com.cpe.weatherapp

import android.Manifest
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.cpe.weatherapp.ui.screen.main.MainScreen
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import java.util.UUID

class MainActivity : ComponentActivity() {

    private val TAG = "FrugalLogs"

    private val REQUEST_ENABLE_BT = 1

    //We will use a Handler to get the BT Connection statys
    var handler: Handler? = null
    private val ERROR_READ = 0 // used in bluetooth handler to identify message update

    var arduinoBTModule: BluetoothDevice? = null
    var arduinoUUID =
        UUID.fromString("00001101-0000-1000-8000-00805F9B34FB") //We declare a default UUID to create the global variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter = bluetoothManager.adapter

        var connectToDeviceEnabled by mutableStateOf(false)
        var devices by mutableStateOf("")

        var readings by mutableStateOf("")

        //Using a handler to update the interface in case of an error connecting to the BT device
        //My idea is to show handler vs RxAndroid
        handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    ERROR_READ -> {
                        readings = msg.obj.toString() // Read message from Arduino
                    }
                }
            }
        }

        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column {

                        Button(
                            onClick = {

                                //Check if the phone supports BT
                                if (bluetoothAdapter == null) {
                                    // Device doesn't support Bluetooth
                                    Log.d(TAG, "Device doesn't support Bluetooth")
                                } else {
                                    Log.d(TAG, "Device support Bluetooth")
                                    //Check BT enabled. If disabled, we ask the user to enable BT
                                    if (!bluetoothAdapter.isEnabled) {
                                        Log.d(TAG, "Bluetooth is disabled")
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
                                            Log.d(TAG, "We don't BT Permissions")
                                            startActivityForResult(
                                                enableBtIntent,
                                                REQUEST_ENABLE_BT
                                            )
                                            Log.d(TAG, "Bluetooth is enabled now")
                                        } else {
                                            Log.d(TAG, "We have BT Permissions")
                                            startActivityForResult(
                                                enableBtIntent,
                                                REQUEST_ENABLE_BT
                                            )
                                            Log.d(TAG, "Bluetooth is enabled now")
                                        }
                                    } else {
                                        Log.d(TAG, "Bluetooth is enabled")
                                    }
                                    var btDevicesString = ""
                                    val pairedDevices = bluetoothAdapter.bondedDevices
                                    if (pairedDevices.size > 0) {
                                        // There are paired devices. Get the name and address of each paired device.
                                        for (device in pairedDevices) {
                                            val deviceName = device.name
                                            val deviceHardwareAddress =
                                                device.address // MAC address
                                            Log.d(TAG, "deviceName:$deviceName")
                                            Log.d(
                                                TAG,
                                                "deviceHardwareAddress:$deviceHardwareAddress"
                                            )
                                            //We append all devices to a String that we will display in the UI
                                            btDevicesString =
                                                "$btDevicesString$deviceName || $deviceHardwareAddress\n"
                                            //If we find the HC 05 device (the Arduino BT module)
                                            //We assign the device value to the Global variable BluetoothDevice
                                            //We enable the button "Connect to HC 05 device"
                                            if (deviceName == "HC-05") {
                                                Log.d(TAG, "HC-05 found")
                                                arduinoUUID = device.uuids[0].uuid
                                                arduinoBTModule = device
                                                //HC -05 Found, enabling the button to read results
                                                connectToDeviceEnabled = true
                                            }
                                            devices = btDevicesString
                                        }
                                    }
                                }
                                Log.d(TAG, "Button Pressed")
                            }
                        ) {
                            Text(text = "Search")
                        }

                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Connect")
                        }

                        MainScreen()

                    }

                }
            }
        }
    }
}