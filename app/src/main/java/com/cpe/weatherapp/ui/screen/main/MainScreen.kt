package com.cpe.weatherapp.ui.screen.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import com.cpe.weatherapp.units.C
import com.cpe.weatherapp.units.percent
import java.time.LocalTime

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    connectionState: ConnectionState,
    onConnectClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val weatherInfo = com.cpe.weatherapp.models.WeatherInfo(
        temperature = 69.0.C,
        humidity = 69.0.percent,
        isRainy = true,
        time = LocalTime.now(),
    )

    val color by animateColorAsState(
        targetValue = when (connectionState) {
            ConnectionState.Disconnected -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.primaryContainer
        }
    )

    Box(modifier = modifier.fillMaxSize()) {
        CenterPill(
            onClick = { if (connectionState == ConnectionState.Disconnected) onConnectClick() },
            modifier = Modifier.align(Alignment.Center),
            color = color,
        ) {
            AnimatedContent(targetState = connectionState) { targetState ->
                when (targetState) {
                    ConnectionState.Disconnected -> Connect()
                    ConnectionState.Connecting -> Connecting()
                    ConnectionState.Connected -> WeatherInfoView(weatherInfo = weatherInfo)
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    WeatherAppTheme {
        MainScreen(
            connectionState = ConnectionState.Disconnected,
            onConnectClick = {},
        )
    }
}