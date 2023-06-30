package com.cpe.weatherapp.units

import kotlin.math.roundToInt

abstract class Temperature {
    abstract fun toString(showFraction: Boolean = true): String
}

val Double.celsius get() = Celsius(this)

data class Celsius(val value: Double): Temperature() {
    override fun toString(showFraction: Boolean) = "${if (showFraction) value else value.roundToInt()}°C"

    fun toFahrenheit() = Fahrenheit(value * 9 / 5 + 32)
}

val Double.fahrenheit get() = Fahrenheit(this)

data class Fahrenheit(val value: Double): Temperature() {
    override fun toString(showFraction: Boolean) = "${if (showFraction) value else value.roundToInt()}°F"

    fun toCelsius() = Celsius((value - 32) * 5 / 9)
}