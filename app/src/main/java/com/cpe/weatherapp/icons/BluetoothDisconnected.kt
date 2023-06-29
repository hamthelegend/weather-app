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

val MaterialSymbols.BluetoothDisabled @Composable get() = rememberBluetoothDisabled()

@Composable
fun rememberBluetoothDisabled(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "bluetooth_disabled",
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
                moveTo(32.5f, 36.458f)
                lineTo(26.042f, 30f)
                lineTo(21.5f, 34.5f)
                quadToRelative(-0.417f, 0.417f, -0.771f, 0.604f)
                quadToRelative(-0.354f, 0.188f, -0.729f, 0.188f)
                quadToRelative(-0.583f, 0f, -0.958f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.959f)
                verticalLineTo(23.167f)
                lineToRelative(-7f, 6.958f)
                quadToRelative(-0.375f, 0.417f, -0.917f, 0.417f)
                reflectiveQuadToRelative(-0.917f, -0.417f)
                quadToRelative(-0.416f, -0.375f, -0.416f, -0.896f)
                reflectiveQuadToRelative(0.416f, -0.937f)
                lineToRelative(7.209f, -7.25f)
                lineTo(3.583f, 7.583f)
                quadToRelative(-0.375f, -0.375f, -0.375f, -0.895f)
                quadToRelative(0f, -0.521f, 0.375f, -0.938f)
                quadToRelative(0.417f, -0.417f, 0.938f, -0.417f)
                quadToRelative(0.521f, 0f, 0.937f, 0.417f)
                lineToRelative(28.875f, 28.875f)
                quadToRelative(0.375f, 0.417f, 0.396f, 0.937f)
                quadToRelative(0.021f, 0.521f, -0.396f, 0.896f)
                quadToRelative(-0.416f, 0.417f, -0.937f, 0.417f)
                quadToRelative(-0.521f, 0f, -0.896f, -0.417f)
                close()
                moveToRelative(-11.208f, -5.541f)
                lineToRelative(2.833f, -2.75f)
                lineToRelative(-2.833f, -2.792f)
                close()
                moveToRelative(1.333f, -11.709f)
                lineToRelative(-1.875f, -1.875f)
                lineToRelative(4.5f, -4.458f)
                lineToRelative(-3.958f, -3.833f)
                verticalLineToRelative(8.833f)
                lineToRelative(-2.625f, -2.625f)
                verticalLineTo(6f)
                quadToRelative(0f, -0.583f, 0.375f, -0.958f)
                reflectiveQuadTo(20f, 4.667f)
                quadToRelative(0.375f, 0f, 0.729f, 0.187f)
                quadToRelative(0.354f, 0.188f, 0.771f, 0.604f)
                lineToRelative(6.542f, 6.5f)
                quadToRelative(0.166f, 0.209f, 0.27f, 0.438f)
                quadToRelative(0.105f, 0.229f, 0.105f, 0.479f)
                quadToRelative(0f, 0.292f, -0.105f, 0.5f)
                quadToRelative(-0.104f, 0.208f, -0.27f, 0.417f)
                close()
            }
        }.build()
    }
}