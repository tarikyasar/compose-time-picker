package com.tarikyasar.compose_time_picker.configuration

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.tarikyasar.compose_time_picker.utils.Constants

object TimePickerDefaults {

    @Composable
    fun shapeConfiguration(
        shape: Shape = Constants.SHAPE,
        backgroundColor: Color = Constants.BACKGROUND_COLOR,
        borderColor: Color = Constants.BORDER_COLOR,
        borderWidth: Dp = Constants.BORDER_WIDTH
    ): ShapeConfiguration = DefaultShapeConfiguration(
        shape = shape,
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        borderWidth = borderWidth
    )

    @Composable
    fun dividerConfiguration(
        showDivider: Boolean = true,
        dividerColor: Color = Constants.DIVIDER_COLOR
    ): DividerConfiguration = DefaultDividerConfiguration(
        showDivider = showDivider,
        dividerColor = dividerColor
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
        val borderWidth: Dp
    ) : ShapeConfiguration {

        override fun shape(): Shape = shape

        override fun backgroundColor(): Color = backgroundColor

        override fun borderColor(): Color = borderColor
        override fun borderWidth(): Dp = borderWidth
    }
}