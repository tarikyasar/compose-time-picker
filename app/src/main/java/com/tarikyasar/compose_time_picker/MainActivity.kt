package com.tarikyasar.compose_time_picker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tarikyasar.compose_time_picker.ui.theme.ComposetimepickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposetimepickerTheme {
                TimePicker()
            }
        }
    }
}