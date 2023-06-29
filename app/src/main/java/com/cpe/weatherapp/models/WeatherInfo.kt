package com.cpe.weatherapp.models

import com.cpe.weatherapp.units.C
import com.cpe.weatherapp.units.Percent
import com.cpe.weatherapp.units.Temperature
import com.cpe.weatherapp.units.percent
import java.time.LocalTime

data class WeatherInfo(
    val temperature: Temperature,
    val humidity: Percent,
    val isRainy: Boolean,
    val time: LocalTime = LocalTime.now(),
) {
    val isDay = time > LocalTime.of(6, 0) && time < LocalTime.of(18, 0)
}

fun String.toWeatherInfo(): WeatherInfo? {
    return try {
        val (temperatureString, humidityString, weatherString) = split(", ")
        val temperature = temperatureString.removePrefix("T: ").removeSuffix("C").toDouble()
        val humidity = humidityString.removePrefix("H: ").removeSuffix("%").toDouble()
        val isRainy = when (weatherString.removePrefix("Weather Status: ").removeSuffix("\r")) {
            "Clear" -> false
            else -> true
        }
        WeatherInfo(temperature.C, humidity.percent, isRainy)
    } catch (exception: Exception) {
        null
    }
}