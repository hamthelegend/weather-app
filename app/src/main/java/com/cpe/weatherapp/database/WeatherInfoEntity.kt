package com.cpe.weatherapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cpe.weatherapp.ui.models.Weather
import com.cpe.weatherapp.ui.models.WeatherInfo
import com.cpe.weatherapp.units.celsius
import com.cpe.weatherapp.units.percent
import java.time.Instant

@Entity(tableName = "weather")
data class WeatherInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val temperatureCelsius: Double,
    val humidityPercent: Double,
    val weather: Weather,
    val epochSecond: Long,
)

fun WeatherInfo.toWeatherInfoEntity() = WeatherInfoEntity(
    temperatureCelsius = temperature.value,
    humidityPercent = humidity.value,
    weather = weather,
    epochSecond = instant.epochSecond,
)

fun WeatherInfoEntity.toWeatherInfo() = WeatherInfo(
    temperature = temperatureCelsius.celsius,
    humidity = humidityPercent.percent,
    weather = weather,
    instant = Instant.ofEpochSecond(epochSecond),
)