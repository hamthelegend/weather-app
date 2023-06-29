package com.cpe.weatherapp.ui.screen.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
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
import com.cpe.weatherapp.icons.BluetoothDisabled
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

@Composable
fun WeatherInfoView(
    weatherInfo: WeatherInfo,
    connectionState: ConnectionState,
    modifier: Modifier = Modifier,
    showFraction: Boolean = false,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
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
            modifier = Modifier.size(128.dp),
        )
        Spacer(height = 16.dp)
        Text(
            text = weatherInfo.temperature.toString(showFraction),
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
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
                textAlign = TextAlign.Start,
            )
        }
        AnimatedVisibility(visible = connectionState == ConnectionState.Disconnected) {
            Column {
                Spacer(height = 32.dp)
                Icon(
                    imageVector = MaterialSymbols.BluetoothDisabled,
                    contentDescription = stringResource(R.string.disconnected),
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

@Preview
@Composable
fun WeatherInfoViewPreview() {
    val weatherInfo = WeatherInfo(
        temperature = 69.0.celsius,
        humidity = 69.0.percent,
        weather = Weather.HeavyRain,
        instant = Instant.now(),
    )

    WeatherAppTheme {
        WeatherInfoView(
            weatherInfo = weatherInfo,
            modifier = Modifier,
            connectionState = ConnectionState.Disconnected,
        )
    }
}