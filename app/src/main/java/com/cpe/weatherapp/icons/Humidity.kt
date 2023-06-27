package com.cpe.weatherapp.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MaterialSymbols.Humidity @Composable get() = rememberHumidityHigh()

@Composable
fun rememberHumidityHigh(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "humidity_high",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(20f, 35.708f)
                quadToRelative(-5.417f, 0f, -9.312f, -3.791f)
                quadToRelative(-3.896f, -3.792f, -3.896f, -9.209f)
                quadToRelative(0f, -2.708f, 1.083f, -5.041f)
                quadToRelative(1.083f, -2.334f, 2.875f, -4.125f)
                lineTo(20f, 4.458f)
                lineToRelative(9.25f, 9.084f)
                quadToRelative(1.792f, 1.791f, 2.875f, 4.125f)
                quadToRelative(1.083f, 2.333f, 1.083f, 5.041f)
                quadToRelative(0f, 5.375f, -3.875f, 9.188f)
                quadToRelative(-3.875f, 3.812f, -9.333f, 3.812f)
                close()
            }
        }.build()
    }
}