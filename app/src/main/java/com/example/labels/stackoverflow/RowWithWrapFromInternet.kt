package com.example.labels.stackoverflow

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RowWithWrap(
    modifier: Modifier = Modifier,
    verticalSpacer: Dp = 0.dp,
    horizontalSpacer: Dp = 0.dp,
    expanded: Boolean,
    countOfFirstLineElements: (Int) -> Unit,
    content: @Composable () -> Unit
) {
    Box(modifier) {
        Layout(
            content = content,
            measurePolicy = rowWithWrapMeasurePolicy(
                verticalSpacer = verticalSpacer,
                horizontalSpacer = horizontalSpacer,
                expanded = expanded,
                countOfFirstLineElements = countOfFirstLineElements
            )
        )
    }
}

@Composable
fun rowWithWrapMeasurePolicy(
    verticalSpacer: Dp = 0.dp,
    horizontalSpacer: Dp = 0.dp,
    expanded: Boolean,
    countOfFirstLineElements: (Int) -> Unit
): MeasurePolicy {
    return remember(verticalSpacer, horizontalSpacer, expanded) {
        MeasurePolicy { measurables: List<Measurable>, constraints: Constraints ->
            val positions = rowWithWrapRelativePositions(
                constraints,
                measurables,
                verticalSpacer,
                horizontalSpacer,
                expanded
            )
            countOfFirstLineElements(positions.size)
            val width = maxOf(positions.maxOf { it.maxXCoordinate }, constraints.minWidth)
            val height = minOf(
                maxOf(positions.maxOf { it.maxYCoordinate }, constraints.minHeight),
                constraints.maxHeight
            )
            layout(width, height) {
                for ((placeable, dx, dy) in positions) {
                    placeable.placeRelative(dx, dy)
                }
            }
        }
    }
}

private fun MeasureScope.rowWithWrapRelativePositions(
    constraints: Constraints,
    measurables: List<Measurable>,
    verticalSpacer: Dp,
    horizontalSpacer: Dp,
    expanded: Boolean
): List<PlaceableRelativePosition> {
    val result = mutableListOf<PlaceableRelativePosition>()
    var x = 0
    var y = 0
    var maxHeight = -1

    for (measurable in measurables) {
        val placeable = measurable.measure(constraints)
        if (placeable.width + x > constraints.maxWidth) {
//            if (!expanded) {
//                return result
//            }
            y += maxHeight + verticalSpacer.roundToPx()
            x = 0
            maxHeight = -1
//затереть то что ниже и раскоментировать то что выше если надо сделать чтобы кнопка свернуть не была частью списка меток
            if (!expanded && (measurable != measurables.last())) {
                val placeableButton = measurables.last().measure(constraints)
                result += PlaceableRelativePosition(
                    placeableButton, x, y
                )
                return result
            }
        }
        result += PlaceableRelativePosition(placeable, x, y)
        x += placeable.width + horizontalSpacer.roundToPx()
        maxHeight = maxOf(maxHeight, placeable.height)
    }

    return result
}

private data class PlaceableRelativePosition(val placable: Placeable, val dx: Int, val dy: Int)

private val PlaceableRelativePosition.maxXCoordinate: Int
    get() = dx + placable.width

private val PlaceableRelativePosition.maxYCoordinate: Int
    get() = dy + placable.height