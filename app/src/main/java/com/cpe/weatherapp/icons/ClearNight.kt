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

val MaterialSymbols.ClearNight @Composable get() = rememberClearNight()

@Composable
fun rememberClearNight(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "clear_night",
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
                moveTo(20f, 33.625f)
                quadToRelative(2.25f, 0f, 4.562f, -0.854f)
                quadToRelative(2.313f, -0.854f, 4.396f, -2.563f)
                quadToRelative(-3.916f, -1.375f, -6.77f, -3.75f)
                quadToRelative(-2.855f, -2.375f, -4.563f, -5.458f)
                quadToRelative(-1.708f, -3.083f, -2.208f, -6.708f)
                quadToRelative(-0.5f, -3.625f, 0.25f, -7.542f)
                quadToRelative(-4.292f, 1.667f, -6.875f, 5.167f)
                quadToRelative(-2.584f, 3.5f, -2.584f, 8.083f)
                quadToRelative(0f, 5.875f, 3.959f, 9.75f)
                quadToRelative(3.958f, 3.875f, 9.833f, 3.875f)
                close()
                moveToRelative(0f, 2.625f)
                quadToRelative(-3.5f, 0f, -6.5f, -1.229f)
                reflectiveQuadToRelative(-5.208f, -3.417f)
                quadToRelative(-2.209f, -2.187f, -3.48f, -5.146f)
                quadTo(3.542f, 23.5f, 3.542f, 20f)
                quadToRelative(0f, -6.083f, 3.854f, -10.625f)
                reflectiveQuadToRelative(9.479f, -5.625f)
                quadToRelative(1.083f, -0.208f, 1.604f, 0.479f)
                quadToRelative(0.521f, 0.688f, 0.146f, 1.854f)
                quadToRelative(-1.125f, 3.5f, -0.75f, 7.084f)
                quadToRelative(0.375f, 3.583f, 2.063f, 6.645f)
                quadToRelative(1.687f, 3.063f, 4.562f, 5.271f)
                quadToRelative(2.875f, 2.209f, 6.75f, 2.917f)
                quadToRelative(1.167f, 0.25f, 1.479f, 1f)
                quadToRelative(0.313f, 0.75f, -0.396f, 1.542f)
                quadToRelative(-2.291f, 2.583f, -5.541f, 4.146f)
                quadTo(23.542f, 36.25f, 20f, 36.25f)
                close()
            }
        }.build()
    }
}