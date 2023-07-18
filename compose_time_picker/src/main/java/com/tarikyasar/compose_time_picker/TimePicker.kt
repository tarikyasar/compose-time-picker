package com.tarikyasar.compose_time_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tarikyasar.compose_time_picker.composable.TimeSelection
import com.tarikyasar.compose_time_picker.configuration.DividerConfiguration
import com.tarikyasar.compose_time_picker.configuration.ShapeConfiguration
import com.tarikyasar.compose_time_picker.configuration.TextConfiguration
import com.tarikyasar.compose_time_picker.configuration.TimePickerDefaults

@Composable
fun TimePicker(
    modifier: Modifier = Modifier,
    dividerConfiguration: DividerConfiguration = TimePickerDefaults.dividerConfiguration(),
    shapeConfiguration: ShapeConfiguration = TimePickerDefaults.shapeConfiguration(),
    textConfiguration: TextConfiguration = TimePickerDefaults.textConfiguration(),
    onTimeSelected: (hour: String, minute: String, second: String) -> Unit,
) {
    var hour by remember { mutableStateOf<String?>(null) }
    var minute by remember { mutableStateOf<String?>(null) }
    var second by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TimeSelection(
            dividerConfiguration = dividerConfiguration,
            shapeConfiguration = shapeConfiguration,
            textConfiguration = textConfiguration,
            range = 24
        ) {
            hour = it.toString()

            if (it != null && minute != null && second != null) {
                onTimeSelected(it.toString(), minute!!, second!!)
            }
        }

        TimeSelection(
            dividerConfiguration = dividerConfiguration,
            shapeConfiguration = shapeConfiguration,
            textConfiguration = textConfiguration,
            range = 60
        ) {
            minute = it.toString()

            if (hour != null && it != null && second != null) {
                onTimeSelected(hour!!, it.toString(), second!!)
            }
        }

        TimeSelection(
            dividerConfiguration = dividerConfiguration,
            shapeConfiguration = shapeConfiguration,
            textConfiguration = textConfiguration,
            range = 60
        ) {
            second = it.toString()

            if (hour != null && minute != null && it != null) {
                onTimeSelected(hour!!, minute!!, it.toString())
            }
        }
    }
}

@Preview
@Composable
fun TimePickerPreview() {
    TimePicker(onTimeSelected = { _, _, _ ->

    })
}