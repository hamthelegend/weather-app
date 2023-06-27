package com.cpe.weatherapp.units

abstract class Temperature {
    abstract fun toString(showFraction: Boolean = true): String
}

val Double.C get() = Celsius(this)

data class Celsius(val value: Double): Temperature() {
    override fun toString(showFraction: Boolean) = "${if (showFraction) value else value.toInt()}°C"

    fun toFahrenheit() = Fahrenheit(value * 9 / 5 + 32)
}

val Double.F get() = Fahrenheit(this)

data class Fahrenheit(val value: Double): Temperature() {
    override fun toString(showFraction: Boolean) = "${if (showFraction) value else value.toInt()}°F"

    fun toCelsius() = Celsius((value - 32) * 5 / 9)
}