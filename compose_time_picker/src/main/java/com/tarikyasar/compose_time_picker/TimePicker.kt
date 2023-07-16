package com.tarikyasar.compose_time_picker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarikyasar.compose_time_picker.configuration.DividerConfiguration
import com.tarikyasar.compose_time_picker.configuration.ShapeConfiguration
import com.tarikyasar.compose_time_picker.configuration.TimePickerDefaults
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo

@Composable
fun TimePicker(
    onTimeSelected: (hour: String, minute: String, second: String) -> Unit,
    dividerConfiguration: DividerConfiguration = TimePickerDefaults.dividerConfiguration(),
    shapeConfiguration: ShapeConfiguration = TimePickerDefaults.shapeConfiguration()
) {
    var hour by remember { mutableStateOf<String?>(null) }
    var minute by remember { mutableStateOf<String?>(null) }
    var second by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        HourSelection(
            dividerConfiguration = dividerConfiguration,
            shapeConfiguration = shapeConfiguration
        ) {
            hour = it.toString()

            if (it != null && minute != null && second != null) {
                onTimeSelected(it.toString(), minute!!, second!!)
            }
        }

        Spacer(modifier = Modifier.width(2.dp))

        MinuteSelection(
            shapeConfiguration = shapeConfiguration
        ) {
            minute = it.toString()

            if (hour != null && it != null && second != null) {
                onTimeSelected(hour!!, it.toString(), second!!)
            }
        }

        Spacer(modifier = Modifier.width(2.dp))

        SecondSelection(
            shapeConfiguration = shapeConfiguration
        ) {
            second = it.toString()

            if (hour != null && minute != null && it != null) {
                onTimeSelected(hour!!, minute!!, it.toString())
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalSnapperApi::class)
@Composable
private fun HourSelection(
    dividerConfiguration: DividerConfiguration,
    shapeConfiguration: ShapeConfiguration,
    timeSelected: (Int?) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val snappedItem = layoutInfo.currentItem

            timeSelected(snappedItem?.index)
        }
    }

    Box(
        modifier = Modifier
            .size(120.dp)
            .background(
                color = shapeConfiguration.backgroundColor(),
                shape = shapeConfiguration.shape(),
            )
            .border(
                border = BorderStroke(
                    shapeConfiguration.borderWidth(),
                    shapeConfiguration.borderColor()
                ),
                shape = shapeConfiguration.shape()
            )
    ) {
        LazyColumn(
            modifier = Modifier.align(Alignment.Center),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(24) {
                Text(
                    text = getTime(it),
                    fontSize = 64.sp,
                    color = if (lazyListState.isScrollInProgress || layoutInfo.currentItem?.index == it) Color.White else Color.Transparent,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            }
        }

        if (dividerConfiguration.showDivider()) {
            Divider(
                modifier = Modifier.align(Alignment.Center),
                color = dividerConfiguration.dividerColor(),
                thickness = 2.dp
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalSnapperApi::class)
@Composable
fun MinuteSelection(
    shapeConfiguration: ShapeConfiguration,
    timeSelected: (Int?) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val snappedItem = layoutInfo.currentItem

            timeSelected(snappedItem?.index)
        }
    }

    Box(
        modifier = Modifier
            .size(120.dp)
            .background(
                color = shapeConfiguration.backgroundColor(),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        LazyColumn(
            modifier = Modifier.align(Alignment.Center),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(60) {
                Text(
                    text = getTime(it),
                    fontSize = 64.sp,
                    color = if (lazyListState.isScrollInProgress || layoutInfo.currentItem?.index == it) Color.White else shapeConfiguration.backgroundColor(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            }
        }

        Divider(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black,
            thickness = 2.dp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalSnapperApi::class)
@Composable
fun SecondSelection(
    shapeConfiguration: ShapeConfiguration,
    timeSelected: (Int?) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val snappedItem = layoutInfo.currentItem

            timeSelected(snappedItem?.index)
        }
    }

    Box(
        modifier = Modifier
            .size(120.dp)
            .background(
                color = shapeConfiguration.backgroundColor(),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        LazyColumn(
            modifier = Modifier.align(Alignment.Center),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(60) {
                Text(
                    text = getTime(it),
                    fontSize = 64.sp,
                    color = if (lazyListState.isScrollInProgress || layoutInfo.currentItem?.index == it) Color.White else shapeConfiguration.backgroundColor(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            }
        }

        Divider(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black,
            thickness = 2.dp
        )
    }
}

private fun getTime(index: Int): String {
    return if (index < 10) {
        "0$index"
    } else {
        "$index"
    }
}