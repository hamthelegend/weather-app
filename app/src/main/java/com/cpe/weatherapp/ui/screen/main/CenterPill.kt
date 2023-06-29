package com.cpe.weatherapp.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cpe.weatherapp.models.WeatherInfo
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import com.cpe.weatherapp.units.C
import com.cpe.weatherapp.units.percent
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterPill(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    content: @Composable () -> Unit,
) {
    Surface(
        onClick = onClick,
        color = color,
        modifier = modifier,
        shape = CircleShape,
    ) {
        Box(modifier = Modifier.padding(64.dp)) {
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
        temperature = 69.0.C,
        humidity = 69.0.percent,
        isRainy = true,
        time = LocalTime.now(),
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