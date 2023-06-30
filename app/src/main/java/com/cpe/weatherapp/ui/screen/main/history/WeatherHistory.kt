package com.cpe.weatherapp.ui.screen.main.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cpe.weatherapp.ui.models.Weather
import com.cpe.weatherapp.ui.models.WeatherInfo
import com.cpe.weatherapp.ui.shared.Spacer
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import com.cpe.weatherapp.units.celsius
import com.cpe.weatherapp.units.percent
import java.time.Instant
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherHistory(
    weatherHistory: List<WeatherInfo>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        item {
            Spacer(modifier = Modifier.statusBarsPadding())
            Spacer(height = 16.dp)
        }
        items(
            items = weatherHistory,
            key = { it.id ?: Random.nextLong() },
        ) { weatherInfo ->
            WeatherInfoCard(
                weatherInfo = weatherInfo,
                modifier = Modifier.padding(horizontal = 16.dp).animateItemPlacement()
            )
            Spacer(height = 16.dp)
        }
        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}

@Preview
@Composable
fun WeatherHistoryPreview() {
    val weatherHistory = Array(20) {
        WeatherInfo(
            temperature = 69.0.celsius,
            humidity = 69.0.percent,
            weather = Weather.LightRain,
            instant = Instant.ofEpochSecond(it.toLong()),
        )
    }
        .toList()
        .sortedByDescending { it.localDateTime }

    WeatherAppTheme {
        WeatherHistory(weatherHistory = weatherHistory)
    }
}