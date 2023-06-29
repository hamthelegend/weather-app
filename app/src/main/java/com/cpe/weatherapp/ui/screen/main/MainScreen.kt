package com.cpe.weatherapp.ui.screen.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cpe.weatherapp.models.WeatherInfo
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import com.cpe.weatherapp.units.C
import com.cpe.weatherapp.units.percent
import java.time.LocalTime

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    weatherInfo: WeatherInfo?,
    connectionState: ConnectionState,
    onConnectClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val color by animateColorAsState(
        targetValue = when (connectionState) {
            ConnectionState.Disconnected -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.primaryContainer
        }
    )

    Box(modifier = modifier.fillMaxSize()) {
        CenterPill(
            onClick = { if (connectionState == ConnectionState.Disconnected) onConnectClick() },
            modifier = Modifier.align(Alignment.Center).animateContentSize(),
            color = color,
        ) {
            if (weatherInfo == null && connectionState == ConnectionState.Disconnected) {
                Connecting()
            } else {
                WeatherInfoView(
                    weatherInfo = weatherInfo!!,
                    connectionState = connectionState,
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    val weatherInfo = WeatherInfo(
        temperature = 69.0.C,
        humidity = 69.0.percent,
        isRainy = true,
        time = LocalTime.now(),
    )

    WeatherAppTheme {
        MainScreen(
            weatherInfo = weatherInfo,
            connectionState = ConnectionState.Disconnected,
            onConnectClick = {},
        )
    }
}