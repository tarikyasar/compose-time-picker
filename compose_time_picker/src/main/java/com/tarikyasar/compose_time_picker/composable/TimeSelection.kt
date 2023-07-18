package com.tarikyasar.compose_time_picker.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tarikyasar.compose_time_picker.configuration.DividerConfiguration
import com.tarikyasar.compose_time_picker.configuration.ShapeConfiguration
import com.tarikyasar.compose_time_picker.configuration.TextConfiguration
import com.tarikyasar.compose_time_picker.configuration.TimePickerDefaults
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo

@OptIn(ExperimentalFoundationApi::class, ExperimentalSnapperApi::class)
@Composable
fun TimeSelection(
    dividerConfiguration: DividerConfiguration,
    shapeConfiguration: ShapeConfiguration,
    textConfiguration: TextConfiguration,
    range: Int,
    modifier: Modifier = Modifier,
    timeSelected: (Int?) -> Unit
) {
    val time = remember { (0 until range).toList() }
    val lazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val snappedItem = layoutInfo.currentItem

            timeSelected(snappedItem?.index)
        }
    }

    Box(
        modifier = modifier
            .size(shapeConfiguration.size())
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
            modifier = Modifier
                .align(Alignment.Center),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState),
            contentPadding = PaddingValues(12.dp)
        ) {
            itemsIndexed(time) { index, time ->
                val displayTime = time % range

                Text(
                    text = displayTime.toString().padStart(2, '0'),
                    fontSize = textConfiguration.fontSize(),
                    color = if (index == lazyListState.firstVisibleItemIndex) {
                        textConfiguration.color().copy(alpha = 0.7f)
                    } else {
                        textConfiguration.color()
                    },
                    textAlign = textConfiguration.textAlign(),
                    fontWeight = textConfiguration.fontWeight(),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        if (dividerConfiguration.showDivider()) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                color = dividerConfiguration.dividerColor(),
                thickness = 2.dp
            )
        }
    }
}

@Preview
@Composable
fun TimeConfigurationPreview() {
    TimeSelection(
        dividerConfiguration = TimePickerDefaults.dividerConfiguration(),
        shapeConfiguration = TimePickerDefaults.shapeConfiguration(),
        textConfiguration = TimePickerDefaults.textConfiguration(),
        range = 24,
        timeSelected = {

        }
    )
}