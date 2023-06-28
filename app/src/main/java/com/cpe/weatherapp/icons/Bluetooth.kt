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

val MaterialSymbols.Bluetooth @Composable get() = rememberBluetooth()

@Composable
fun rememberBluetooth(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "bluetooth",
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
                moveTo(18.667f, 23.167f)
                lineToRelative(-6.959f, 6.958f)
                quadToRelative(-0.416f, 0.417f, -0.958f, 0.417f)
                reflectiveQuadToRelative(-0.917f, -0.417f)
                quadToRelative(-0.416f, -0.375f, -0.416f, -0.896f)
                reflectiveQuadToRelative(0.416f, -0.937f)
                lineTo(18.125f, 20f)
                lineToRelative(-8.292f, -8.333f)
                quadToRelative(-0.416f, -0.375f, -0.416f, -0.917f)
                reflectiveQuadToRelative(0.416f, -0.917f)
                quadToRelative(0.375f, -0.416f, 0.917f, -0.416f)
                reflectiveQuadToRelative(0.958f, 0.416f)
                lineToRelative(6.959f, 6.959f)
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
                lineToRelative(-6.209f, 6.166f)
                lineToRelative(6.209f, 6.209f)
                quadToRelative(0.166f, 0.208f, 0.27f, 0.437f)
                quadToRelative(0.105f, 0.229f, 0.105f, 0.479f)
                quadToRelative(0f, 0.25f, -0.105f, 0.479f)
                quadToRelative(-0.104f, 0.23f, -0.27f, 0.438f)
                lineTo(21.5f, 34.5f)
                quadToRelative(-0.417f, 0.417f, -0.771f, 0.604f)
                quadToRelative(-0.354f, 0.188f, -0.729f, 0.188f)
                quadToRelative(-0.583f, 0f, -0.958f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.959f)
                close()
                moveToRelative(2.625f, -6.375f)
                lineToRelative(3.958f, -3.917f)
                lineToRelative(-3.958f, -3.833f)
                close()
                moveToRelative(0f, 14.125f)
                lineToRelative(3.958f, -3.834f)
                lineToRelative(-3.958f, -3.916f)
                close()
            }
        }.build()
    }
}