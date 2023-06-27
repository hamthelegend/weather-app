package com.cpe.weatherapp.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import com.cpe.weatherapp.units.C
import com.cpe.weatherapp.units.percent
import java.time.LocalTime

@Composable
fun MainScreen() {
    val weatherInfo = com.cpe.weatherapp.models.WeatherInfo(
        temperature = 69.0.C,
        humidity = 69.0.percent,
        isRainy = true,
        time = LocalTime.now(),
    )

    Box(modifier = Modifier.fillMaxSize()) {
        WeatherInfo(weatherInfo = weatherInfo, modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    WeatherAppTheme {
        MainScreen()
    }
}