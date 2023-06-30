package com.cpe.weatherapp.ui.models

import com.cpe.weatherapp.units.Celsius
import com.cpe.weatherapp.units.Percent
import com.cpe.weatherapp.units.celsius
import com.cpe.weatherapp.units.percent
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

@Suppress("MemberVisibilityCanBePrivate")
data class WeatherInfo(
    val id: Long? = null,
    val temperature: Celsius,
    val humidity: Percent,
    val weather: Weather,
    val instant: Instant = Instant.now(),
) {
    val localDateTime: LocalDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()

    val isDay = localDateTime.toLocalTime() > LocalTime.of(6, 0)
            && localDateTime.toLocalTime() < LocalTime.of(18, 0)
}

fun String.toWeatherInfo(): WeatherInfo? {
    return try {
        val (temperatureString, humidityString, weatherString) = split(", ")
        val temperature = temperatureString.removePrefix("T: ").removeSuffix("C").toDouble()
        val humidity = humidityString.removePrefix("H: ").removeSuffix("%").toDouble()
        val weather = when (weatherString.removePrefix("Weather Status: ").removeSuffix("\r")) {
            "Clear" -> Weather.Clear
            "Light Raining" -> Weather.LightRain
            "Heavy Raining" -> Weather.HeavyRain
            else -> Weather.Clear
        }
        WeatherInfo(
            temperature = temperature.celsius,
            humidity = humidity.percent,
            weather = weather,
        )
    } catch (exception: Exception) {
        null
    }
}