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

val MaterialSymbols.RainyHeavy @Composable get() = rememberRainyHeavy()

@Composable
fun rememberRainyHeavy(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "rainy_heavy",
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
                moveTo(334.085f, -211.823f)
                quadToRelative(-11.752f, 6.489f, -24.258f, 2.246f)
                quadToRelative(-12.506f, -4.244f, -17.994f, -15.756f)
                lineToRelative(-240f, -480f)
                quadTo(46f, -717f, 49.932f, -729.844f)
                quadToRelative(3.93f, -12.844f, 15.5f, -18.666f)
                quadTo(77f, -754f, 89.84f, -750.09f)
                quadToRelative(12.839f, 3.911f, 18.66f, 15.423f)
                lineToRelative(240.167f, 480f)
                quadToRelative(5.833f, 11.667f, 1.502f, 24.178f)
                quadToRelative(-4.332f, 12.51f, -16.084f, 18.666f)
                close()
                moveToRelative(186.817f, 0f)
                quadToRelative(-11.569f, 6.489f, -24.107f, 2.246f)
                quadToRelative(-12.538f, -4.244f, -18.462f, -15.756f)
                lineToRelative(-240f, -480f)
                quadTo(232.5f, -717f, 236.498f, -729.844f)
                reflectiveQuadToRelative(15.75f, -18.666f)
                quadTo(264f, -754f, 276.377f, -750.09f)
                quadToRelative(12.377f, 3.911f, 18.29f, 15.423f)
                lineToRelative(240.833f, 480f)
                quadToRelative(5.5f, 11.667f, 1.236f, 24.178f)
                quadToRelative(-4.265f, 12.51f, -15.834f, 18.666f)
                close()
                moveToRelative(373.85f, 0f)
                quadTo(883f, -206f, 870.16f, -210.183f)
                quadToRelative(-12.839f, -4.183f, -18.327f, -16.317f)
                lineToRelative(-240f, -480f)
                quadToRelative(-5.833f, -11.667f, -1.668f, -24.011f)
                quadToRelative(4.164f, -12.343f, 16.25f, -18.166f)
                quadToRelative(11.752f, -5.823f, 24.091f, -1.807f)
                quadToRelative(12.34f, 4.016f, 18.161f, 15.817f)
                lineToRelative(240f, 480f)
                quadToRelative(5.833f, 11.667f, 1.835f, 24.511f)
                reflectiveQuadToRelative(-15.75f, 18.333f)
                close()
                moveToRelative(-186.833f, -0.576f)
                quadToRelative(-12.086f, 6.565f, -24.419f, 2.232f)
                reflectiveQuadTo(665.333f, -226.5f)
                lineToRelative(-240f, -480f)
                quadToRelative(-5.833f, -11.667f, -1.835f, -24.011f)
                quadToRelative(3.998f, -12.343f, 15.75f, -18.166f)
                quadToRelative(11.752f, -5.823f, 24.129f, -1.807f)
                reflectiveQuadToRelative(18.29f, 15.817f)
                lineToRelative(240.833f, 480f)
                quadToRelative(5.5f, 11.667f, 1.335f, 23.685f)
                quadToRelative(-4.164f, 12.018f, -15.916f, 18.583f)
                close()
            }
        }.build()
    }
}