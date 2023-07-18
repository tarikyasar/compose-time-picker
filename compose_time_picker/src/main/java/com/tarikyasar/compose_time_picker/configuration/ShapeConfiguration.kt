package com.tarikyasar.compose_time_picker.configuration

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

interface ShapeConfiguration {
    fun shape(): Shape

    fun backgroundColor(): Color

    fun borderColor(): Color

    fun borderWidth(): Dp

    fun size(): Dp
}