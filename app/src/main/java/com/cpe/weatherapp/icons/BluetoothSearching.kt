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

val MaterialSymbols.BluetoothSearching @Composable get() = rememberBluetoothSearching()

@Composable
fun rememberBluetoothSearching(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "bluetooth_searching",
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
                moveTo(26.5f, 22.583f)
                lineToRelative(-2.125f, -2.125f)
                quadToRelative(-0.208f, -0.166f, -0.208f, -0.437f)
                reflectiveQuadToRelative(0.208f, -0.479f)
                lineToRelative(2.083f, -2.084f)
                quadToRelative(0.417f, -0.416f, 0.854f, -0.312f)
                quadToRelative(0.438f, 0.104f, 0.563f, 0.646f)
                quadToRelative(0.125f, 0.583f, 0.208f, 1.104f)
                quadToRelative(0.084f, 0.521f, 0.084f, 1.104f)
                quadToRelative(0f, 0.583f, -0.084f, 1.146f)
                quadToRelative(-0.083f, 0.562f, -0.25f, 1.104f)
                quadToRelative(-0.125f, 0.583f, -0.541f, 0.667f)
                quadToRelative(-0.417f, 0.083f, -0.792f, -0.334f)
                close()
                moveToRelative(4.375f, 4.334f)
                quadToRelative(-0.292f, -0.292f, -0.313f, -0.646f)
                quadToRelative(-0.02f, -0.354f, 0.188f, -0.729f)
                quadToRelative(0.583f, -1.292f, 0.896f, -2.688f)
                quadToRelative(0.312f, -1.396f, 0.312f, -2.896f)
                quadToRelative(0f, -1.416f, -0.333f, -2.833f)
                quadToRelative(-0.333f, -1.417f, -0.917f, -2.708f)
                quadToRelative(-0.166f, -0.375f, -0.125f, -0.771f)
                quadToRelative(0.042f, -0.396f, 0.375f, -0.729f)
                quadToRelative(0.459f, -0.459f, 1.167f, -0.334f)
                reflectiveQuadToRelative(1.125f, 1f)
                quadToRelative(0.625f, 1.375f, 0.979f, 3.021f)
                reflectiveQuadToRelative(0.354f, 3.354f)
                quadToRelative(0f, 1.709f, -0.354f, 3.334f)
                reflectiveQuadToRelative(-1.021f, 3.166f)
                quadToRelative(-0.375f, 0.75f, -1.083f, 0.875f)
                reflectiveQuadToRelative(-1.25f, -0.416f)
                close()
                moveToRelative(-15.667f, -3.75f)
                lineToRelative(-7f, 6.958f)
                quadToRelative(-0.416f, 0.417f, -0.937f, 0.417f)
                quadToRelative(-0.521f, 0f, -0.938f, -0.417f)
                quadToRelative(-0.375f, -0.375f, -0.375f, -0.896f)
                reflectiveQuadToRelative(0.375f, -0.937f)
                lineTo(14.667f, 20f)
                lineToRelative(-8.334f, -8.333f)
                quadToRelative(-0.375f, -0.375f, -0.375f, -0.917f)
                reflectiveQuadToRelative(0.375f, -0.917f)
                quadToRelative(0.417f, -0.416f, 0.938f, -0.416f)
                quadToRelative(0.521f, 0f, 0.937f, 0.416f)
                lineToRelative(7f, 6.959f)
                verticalLineTo(6f)
                quadToRelative(0f, -0.583f, 0.354f, -0.958f)
                quadToRelative(0.355f, -0.375f, 0.938f, -0.375f)
                quadToRelative(0.375f, 0f, 0.75f, 0.187f)
                quadToRelative(0.375f, 0.188f, 0.792f, 0.604f)
                lineToRelative(6.5f, 6.5f)
                quadToRelative(0.208f, 0.209f, 0.312f, 0.438f)
                quadToRelative(0.104f, 0.229f, 0.104f, 0.479f)
                quadToRelative(0f, 0.292f, -0.104f, 0.5f)
                quadToRelative(-0.104f, 0.208f, -0.312f, 0.417f)
                lineToRelative(-6.167f, 6.166f)
                lineToRelative(6.167f, 6.209f)
                quadToRelative(0.208f, 0.208f, 0.312f, 0.437f)
                quadToRelative(0.104f, 0.229f, 0.104f, 0.479f)
                quadToRelative(0f, 0.25f, -0.104f, 0.479f)
                quadToRelative(-0.104f, 0.23f, -0.312f, 0.438f)
                lineToRelative(-6.5f, 6.5f)
                quadToRelative(-0.417f, 0.417f, -0.792f, 0.604f)
                quadToRelative(-0.375f, 0.188f, -0.75f, 0.188f)
                quadToRelative(-0.583f, 0f, -0.938f, -0.375f)
                quadToRelative(-0.354f, -0.375f, -0.354f, -0.959f)
                close()
                moveToRelative(2.625f, -6.375f)
                lineToRelative(3.959f, -3.917f)
                lineToRelative(-3.959f, -3.833f)
                close()
                moveToRelative(0f, 14.125f)
                lineToRelative(3.959f, -3.834f)
                lineToRelative(-3.959f, -3.916f)
                close()
            }
        }.build()
    }
}