package com.tarikyasar.compose_time_picker.configuration

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.tarikyasar.compose_time_picker.utils.Constants

object TimePickerDefaults {

    @Composable
    fun shapeConfiguration(
        shape: Shape = Constants.SHAPE,
        backgroundColor: Color = Constants.BACKGROUND_COLOR,
        borderColor: Color = Constants.BORDER_COLOR,
        borderWidth: Dp = Constants.BORDER_WIDTH,
        size: Dp = Constants.SHAPE_SIZE
    ): ShapeConfiguration = DefaultShapeConfiguration(
        shape = shape,
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        borderWidth = borderWidth,
        size = size
    )

    @Composable
    fun dividerConfiguration(
        showDivider: Boolean = true,
        dividerColor: Color = Constants.DIVIDER_COLOR
    ): DividerConfiguration = DefaultDividerConfiguration(
        showDivider = showDivider,
        dividerColor = dividerColor
    )

    @Composable
    fun textConfiguration(
        fontSize: TextUnit = 64.sp,
        fontWeight: FontWeight = FontWeight.Normal,
        color: Color = Color.White,
        textAlign: TextAlign = TextAlign.Center
    ): TextConfiguration = DefaultTextConfiguration(
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color,
        textAlign = textAlign
    )

    private class DefaultDividerConfiguration(
        val dividerColor: Color,
        val showDivider: Boolean
    ) : DividerConfiguration {

        override fun dividerColor(): Color = dividerColor

        override fun showDivider(): Boolean = showDivider
    }

    private class DefaultShapeConfiguration(
        val shape: Shape,
        val backgroundColor: Color,
        val borderColor: Color,
        val borderWidth: Dp,
        val size: Dp
    ) : ShapeConfiguration {

        override fun shape(): Shape = shape

        override fun backgroundColor(): Color = backgroundColor

        override fun borderColor(): Color = borderColor
        override fun borderWidth(): Dp = borderWidth

        override fun size(): Dp = size
    }

    private class DefaultTextConfiguration(
        val fontSize: TextUnit,
        val fontWeight: FontWeight,
        val color: Color,
        val textAlign: TextAlign
    ) : TextConfiguration {
        override fun fontSize(): TextUnit = fontSize

        override fun fontWeight(): FontWeight = fontWeight

        override fun color(): Color = color

        override fun textAlign(): TextAlign = textAlign

    }
}