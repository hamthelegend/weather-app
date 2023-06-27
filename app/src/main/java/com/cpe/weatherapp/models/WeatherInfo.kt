package com.cpe.weatherapp.models

import com.cpe.weatherapp.units.Percent
import com.cpe.weatherapp.units.Temperature
import java.time.LocalTime

data class WeatherInfo(
    val temperature: Temperature,
    val humidity: Percent,
    val isRainy: Boolean,
    val time: LocalTime,
) {
    val isDay = time > LocalTime.of(6, 0) && time < LocalTime.of(18, 0)
}