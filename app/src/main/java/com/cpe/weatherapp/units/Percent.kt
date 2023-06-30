package com.cpe.weatherapp.units

import kotlin.math.roundToInt

val Double.percent get() = Percent(this)

data class Percent(val value: Double) {
    fun toString(showFraction: Boolean = true) = "${if (showFraction) value else value.roundToInt()}%"
}