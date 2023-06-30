package com.cpe.weatherapp.ui.screen.main.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cpe.weatherapp.R
import com.cpe.weatherapp.icons.ClearDay
import com.cpe.weatherapp.icons.ClearNight
import com.cpe.weatherapp.icons.Humidity
import com.cpe.weatherapp.icons.MaterialSymbols
import com.cpe.weatherapp.icons.Rainy
import com.cpe.weatherapp.icons.RainyHeavy
import com.cpe.weatherapp.ui.models.Weather
import com.cpe.weatherapp.ui.models.WeatherInfo
import com.cpe.weatherapp.ui.shared.Spacer
import com.cpe.weatherapp.ui.theme.WeatherAppTheme
import com.cpe.weatherapp.units.celsius
import com.cpe.weatherapp.units.percent
import java.time.Instant
import java.time.format.DateTimeFormatter

@Composable
fun WeatherInfoCard(
    weatherInfo: WeatherInfo,
    modifier: Modifier = Modifier,
    showFraction: Boolean = false,
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = when (weatherInfo.weather) {
                    Weather.Clear ->
                        if (weatherInfo.isDay) MaterialSymbols.ClearDay else MaterialSymbols.ClearNight
                    Weather.LightRain -> MaterialSymbols.Rainy
                    Weather.HeavyRain -> MaterialSymbols.RainyHeavy
                },
                contentDescription = when (weatherInfo.weather) {
                    Weather.Clear -> stringResource(id = R.string.clear)
                    Weather.LightRain -> stringResource(id = R.string.light_rain)
                    Weather.HeavyRain -> stringResource(id = R.string.heavy_rain)
                },
                modifier = Modifier.size(32.dp),
            )
            Spacer(width = 16.dp)
            Column {
                Text(
                    text = weatherInfo.temperature.toString(showFraction),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = MaterialSymbols.Humidity,
                        contentDescription = stringResource(R.string.humidity),
                        modifier = Modifier.size(12.dp),
                    )
                    Spacer(width = 4.dp)
                    Text(
                        text = weatherInfo.humidity.toString(showFraction),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Start,
                    )
                }
            }
            Spacer(weight = 1f)
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = weatherInfo.localDateTime.toLocalDate().format(
                        DateTimeFormatter.ofPattern("LLL d, yyyy")
                    ),
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = weatherInfo.localDateTime.toLocalTime().format(
                        DateTimeFormatter.ofPattern("hh:mm:ss a")
                    ),
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

@Preview
@Composable
fun WeatherInfoCardPreview() {
    val weatherInfo = WeatherInfo(
        temperature = 69.0.celsius,
        humidity = 69.0.percent,
        weather = Weather.LightRain,
        instant = Instant.now(),
    )

    WeatherAppTheme {
        WeatherInfoCard(
            weatherInfo = weatherInfo,
            modifier = Modifier,
        )
    }
}