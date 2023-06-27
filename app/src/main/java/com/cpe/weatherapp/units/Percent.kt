package com.cpe.weatherapp.units

val Double.percent get() = Percent(this)

data class Percent(val value: Double) {
    fun toString(showFraction: Boolean = true) = "${if (showFraction) value else value.toInt()}%"
}