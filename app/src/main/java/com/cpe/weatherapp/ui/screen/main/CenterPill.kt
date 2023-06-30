package com.cpe.weatherapp.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cpe.weatherapp.ui.models.Weather
import com.cpe.weatherapp.ui.models.WeatherInfo
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import com.cpe.weatherapp.units.celsius
import com.cpe.weatherapp.units.percent
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterPill(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    cornerRadiusPercent: Int = 50,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    content: @Composable () -> Unit,
) {
    Surface(
        onClick = onClick,
        color = color,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadiusPercent),
    ) {
        Box(modifier = Modifier) {
            content()
        }
    }
}

@Preview
@Composable
fun CenterPillConnectPreview() {
    WeatherAppTheme {
        CenterPill(
            onClick = {},
            color = MaterialTheme.colorScheme.primary,
        ) {
            Connect()
        }
    }
}


@Preview
@Composable
fun CenterPillConnectingPreview() {
    WeatherAppTheme {
        CenterPill(
            onClick = {},
            color = MaterialTheme.colorScheme.primaryContainer,
        ) {
            Connecting()
        }
    }
}

@Preview
@Composable
fun CenterPillWeatherInfoPreview() {
    val weatherInfo = WeatherInfo(
        temperature = 69.0.celsius,
        humidity = 69.0.percent,
        weather = Weather.HeavyRain,
        instant = Instant.now(),
    )

    WeatherAppTheme {
        CenterPill(
            onClick = {},
            color = MaterialTheme.colorScheme.primary,
        ) {
            WeatherInfoView(
                weatherInfo = weatherInfo,
                connectionState = ConnectionState.Disconnected
            )
        }
    }
}