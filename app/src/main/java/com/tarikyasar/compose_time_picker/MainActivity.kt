package com.tarikyasar.compose_time_picker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.tarikyasar.compose_time_picker.configuration.TextConfiguration
import com.tarikyasar.compose_time_picker.configuration.TimePickerDefaults
import com.tarikyasar.compose_time_picker.ui.theme.ComposetimepickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposetimepickerTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TimePicker(
                        onTimeSelected = { h, m, s ->
                            println("$h:$m:$s")
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}