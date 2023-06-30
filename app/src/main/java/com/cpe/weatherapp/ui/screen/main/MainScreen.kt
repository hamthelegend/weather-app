package com.cpe.weatherapp.ui.screen.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cpe.weatherapp.MainViewModel
import com.cpe.weatherapp.ui.models.Weather
import com.cpe.weatherapp.ui.models.WeatherInfo
import com.cpe.weatherapp.ui.screen.main.history.WeatherHistory
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import com.cpe.weatherapp.units.celsius
import com.cpe.weatherapp.units.percent
import java.time.Instant

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val requestBluetoothPermission = {}
    val startEnableBluetoothActivity = {}

    val weatherHistory by viewModel.weatherHistory.collectAsState(initial = emptyList())

    LaunchedEffect(true) {
        viewModel.init(requestBluetoothPermission, startEnableBluetoothActivity)
    }

    val connectionState = when (viewModel.readings) {
        "Unable to connect to the BT device", "" -> ConnectionState.Disconnected
        else -> ConnectionState.Connected
    }

    val color by animateColorAsState(
        targetValue = when {
            viewModel.showHistory -> MaterialTheme.colorScheme.surface
            connectionState == ConnectionState.Disconnected -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.primary
        }
    )

    val cornerRadiusPercent by animateIntAsState(
        targetValue = if (!viewModel.showHistory) 50 else 0,
    )

    Surface(color = MaterialTheme.colorScheme.surface) {
        Box(modifier = modifier.fillMaxSize()) {
            CenterPill(
                cornerRadiusPercent = cornerRadiusPercent,
                onClick = viewModel::toggleShowHistory,
                modifier = Modifier.align(Alignment.Center),
                color = color,
            ) {
                AnimatedContent(
                    targetState = viewModel.weatherInfo to viewModel.showHistory,
                    transitionSpec = {
                        val initialWeatherInfo = initialState.first
                        val targetWeatherInfo = targetState.first
                        val initialShowHistory = initialState.second
                        val targetShowHistory = targetState.second
                        if (initialWeatherInfo == null && targetWeatherInfo != null ||
                            initialShowHistory != targetShowHistory) {
                            fadeIn(animationSpec = tween(220, delayMillis = 90)) +
                                    scaleIn(
                                        initialScale = 0.92f,
                                        animationSpec = tween(220, delayMillis = 90),
                                    ) with
                                    fadeOut(animationSpec = tween(90))
                        } else {
                            fadeIn(initialAlpha = 1f) with fadeOut(targetAlpha = 1f)
                        }
                    },
                ) { (targetWeatherInfo, showHistory) ->
                    if (!showHistory) {
                        if (targetWeatherInfo == null) {
                            Connecting(modifier = Modifier.padding(64.dp))
                        } else {
                            WeatherInfoView(
                                weatherInfo = targetWeatherInfo,
                                connectionState = connectionState,
                                modifier = Modifier.padding(64.dp),
                            )
                        }
                    } else {
                        WeatherHistory(weatherHistory = weatherHistory)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    val weatherInfo = WeatherInfo(
        temperature = 69.0.celsius,
        humidity = 69.0.percent,
        weather = Weather.Clear,
        instant = Instant.now(),
    )

    WeatherAppTheme {
        MainScreen()
    }
}