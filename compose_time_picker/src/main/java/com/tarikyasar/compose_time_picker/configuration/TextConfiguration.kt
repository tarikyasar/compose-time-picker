package com.tarikyasar.compose_time_picker.configuration

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

interface TextConfiguration {
    fun fontSize(): TextUnit

    fun fontWeight(): FontWeight

    fun color(): Color

    fun textAlign(): TextAlign
}