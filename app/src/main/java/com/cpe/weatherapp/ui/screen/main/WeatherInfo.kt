package com.cpe.weatherapp.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cpe.weatherapp.R
import com.cpe.weatherapp.icons.ClearDay
import com.cpe.weatherapp.icons.ClearNight
import com.cpe.weatherapp.icons.Humidity
import com.cpe.weatherapp.icons.MaterialSymbols
import com.cpe.weatherapp.icons.Rainy
import com.cpe.weatherapp.models.WeatherInfo
import com.cpe.weatherapp.ui.shared.IconText
import com.cpe.weatherapp.ui.shared.Spacer
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import com.cpe.weatherapp.units.C
import com.cpe.weatherapp.units.percent
import java.time.LocalTime

/*@Composable
fun WeatherInfo(
    weatherInfo: WeatherInfo,
    modifier: Modifier = Modifier,
    showFraction: Boolean = false,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 64.dp, vertical = 32.dp),
        ) {
            Column {
                Text(
                    text = weatherInfo.temperature.toString(showFraction),
                    style = MaterialTheme.typography.displayMedium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = MaterialSymbols.Humidity,
                        contentDescription = stringResource(R.string.humidity),
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(width = 4.dp)
                    Text(
                        text = weatherInfo.humidity.toString(showFraction),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
            Spacer(width = 32.dp)
            Icon(
                imageVector = if (weatherInfo.isRainy) {
                    MaterialSymbols.Rainy
                } else {
                    if (weatherInfo.isDay) MaterialSymbols.ClearDay else MaterialSymbols.ClearNight
                },
                contentDescription = if (weatherInfo.isRainy) {
                    stringResource(R.string.rainy)
                } else {
                    stringResource(R.string.clear)
                },
                modifier = Modifier.size(64.dp)
            )
        }
    }
}*/

@Composable
fun WeatherInfo(
    weatherInfo: WeatherInfo,
    modifier: Modifier = Modifier,
    showFraction: Boolean = false,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = CircleShape,
    ) {
        Column(
            modifier = Modifier.padding(64.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = if (weatherInfo.isRainy) {
                    MaterialSymbols.Rainy
                } else {
                    if (weatherInfo.isDay) MaterialSymbols.ClearDay else MaterialSymbols.ClearNight
                },
                contentDescription = if (weatherInfo.isRainy) {
                    stringResource(R.string.rainy)
                } else {
                    stringResource(R.string.clear)
                },
                modifier = Modifier.size(128.dp),
            )
            Spacer(height = 16.dp)
            Text(
                text = weatherInfo.temperature.toString(showFraction),
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(height = 16.dp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = MaterialSymbols.Humidity,
                    contentDescription = stringResource(R.string.humidity),
                    modifier = Modifier.size(24.dp),
                )
                Spacer(width = 8.dp)
                Text(
                    text = weatherInfo.humidity.toString(showFraction),
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
    }
}

@Preview
@Composable
fun WeatherInfoPreview() {
    val weatherInfo = WeatherInfo(
        temperature = 69.0.C,
        humidity = 69.0.percent,
        isRainy = true,
        time = LocalTime.now(),
    )

    WeatherAppTheme {
        WeatherInfo(
            weatherInfo = weatherInfo,
            modifier = Modifier,
        )
    }
}