package com.cpe.weatherapp.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.cpe.weatherapp.icons.Bluetooth
import com.cpe.weatherapp.icons.MaterialSymbols
import com.cpe.weatherapp.ui.shared.Spacer
import com.cpe.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun Connect(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = MaterialSymbols.Bluetooth,
            contentDescription = stringResource(R.string.connect),
            modifier = Modifier.size(128.dp),
        )
        Spacer(height = 16.dp)
        Text(
            text = stringResource(R.string.connect),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun ConnectPreview() {
    WeatherAppTheme {
        Connect()
    }
}