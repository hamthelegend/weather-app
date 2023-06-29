package com.cpe.weatherapp.ui.screen.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.cpe.weatherapp.MainViewModel
import com.cpe.weatherapp.ui.models.Weather
import com.cpe.weatherapp.ui.models.WeatherInfo
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import com.cpe.weatherapp.units.celsius
import com.cpe.weatherapp.units.percent
import java.time.Instant

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    weatherInfo: WeatherInfo?,
    connectionState: ConnectionState,
    onConnectClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {

    val color by animateColorAsState(
        targetValue = when (connectionState) {
            ConnectionState.Disconnected -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.primary
        }
    )

    Box(modifier = modifier.fillMaxSize()) {
        CenterPill(
            onClick = { if (connectionState == ConnectionState.Disconnected) onConnectClick() },
            modifier = Modifier.align(Alignment.Center),
            color = color,
        ) {
            AnimatedContent(
                targetState = weatherInfo,
                transitionSpec = {
                    if (initialState == null && targetState != null) {
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
            ) { targetWeatherInfo ->
                if (targetWeatherInfo == null) {
                    Connecting()
                } else {
                    WeatherInfoView(
                        weatherInfo = targetWeatherInfo,
                        connectionState = connectionState,
                    )
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
        MainScreen(
            weatherInfo = weatherInfo,
            connectionState = ConnectionState.Disconnected,
            onConnectClick = {},
        )
    }
}