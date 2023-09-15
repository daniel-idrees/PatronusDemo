package com.patronusgroup.presentation.views

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.graphics.PathParser.createPathFromPathData
import java.util.regex.Pattern

/**
 * https://blog.devgenius.io/custom-shapes-in-jetpack-compose-deep-dive-b987a52c743c
 */
internal class MapShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val pathData =
            """M163.22 0.509268C212.182 2.92411 259.158 21.351 289.89 59.4005C320.789 97.6561 336.34 149.245 321.059 195.928C306.966 238.978 261.227 258.633 219.104 275.645C181.395 290.874 141.042 299.169 103.373 283.844C62.6263 267.266 31.3354 234.718 16.786 193.335C-0.35287 144.585 -11.5002 87.2709 19.7817 46.0786C51.6379 4.13018 110.485 -2.09164 163.22 0.509268Z"""
        val scaleX = size.width / 327F
        val scaleY = size.height / 292F
        return Outline.Generic(
            createPathFromPathData(
                resize(
                    pathData,
                    scaleX,
                    scaleY,
                ),
            ).asComposePath(),
        )
    }

    private fun resize(pathData: String, scaleX: Float, scaleY: Float): String {
        val matcher = Pattern.compile("[0-9]+[.]?([0-9]+)?")
            .matcher(pathData) // match the numbers in the path
        val stringBuffer = StringBuffer()
        var count = 0
        while (matcher.find()) {
            val number = matcher.group().toFloat()
            matcher.appendReplacement(
                stringBuffer,
                (if (count % 2 == 0) number * scaleX else number * scaleY).toString(),
            ) // replace numbers with scaled numbers
            ++count
        }
        return stringBuffer.toString() // return the scaled path
    }
}
