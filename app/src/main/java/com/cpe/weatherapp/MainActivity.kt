package com.cpe.weatherapp

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.cpe.weatherapp.ui.screen.main.ConnectionState
import com.cpe.weatherapp.ui.screen.main.MainScreen
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.launch
import java.util.UUID

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val coroutineScope = rememberCoroutineScope()

            var connectionState by remember { mutableStateOf(ConnectionState.Disconnected) }

            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        MainScreen(
                            connectionState = connectionState,
                            onConnectClick = {
                                coroutineScope.launch {
                                    connectionState = ConnectionState.Connecting
                                    val timer = object: CountDownTimer(5_000, 1000) {
                                        override fun onTick(millisUntilFinished: Long) {}
                                        override fun onFinish() { connectionState = ConnectionState.Connected }
                                    }
                                    timer.start()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}