package com.tarikyasar.compose_time_picker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TimePicker() {
    Row {
        Box {
            Text(text = "12", fontSize = 36.sp)
        }

        Box {
            Text(text = "45", fontSize = 36.sp)
        }
    }
}

@Preview
@Composable
private fun TimePickerPreview() {
    TimePicker()
}