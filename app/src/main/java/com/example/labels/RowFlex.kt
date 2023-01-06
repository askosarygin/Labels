package com.example.labels

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RowFlex(
    modifier: Modifier = Modifier,
    verticalSpace: Dp = 0.dp,
    horizontalSpace: Dp = 0.dp,
    maxLinesWhenHidden: Int = 1,
    amountVisibleElementsWhenHidden: ((Int) -> Unit)? = null,
    isExpanded: Boolean = true,
    expandButton: @Composable (() -> Unit)? = null,
    shrinkButton: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    if (expandButton != null && shrinkButton == null) {
        throw RuntimeException("Определите shrinkButton")
    }
    if (expandButton == null && shrinkButton != null) {
        throw RuntimeException("Определите expandButton")
    }

    val buttonsAdded by remember {
        mutableStateOf(expandButton != null)
    }
    Box(modifier = modifier) {
        Layout(
            content = {
                content()
                if (shrinkButton != null && isExpanded) {
                    shrinkButton()
                }
                if (expandButton != null && !isExpanded) {
                    expandButton()
                }
            },
            measurePolicy = rowFlexMeasurePolicy(
                verticalSpace,
                horizontalSpace,
                maxLinesWhenHidden,
                amountVisibleElementsWhenHidden,
                isExpanded,
                buttonsAdded
            )
        )
    }
}

@Composable
private fun rowFlexMeasurePolicy(
    verticalSpace: Dp = 0.dp,
    horizontalSpace: Dp = 0.dp,
    maxLinesWhenHidden: Int = 1,
    amountVisibleElementsWhenHidden: ((Int) -> Unit)?,
    isExpanded: Boolean = true,
    buttonsAdded: Boolean
): MeasurePolicy {
    return remember(
        key1 = verticalSpace,
        key2 = horizontalSpace,
        key3 = isExpanded
    ) {
        MeasurePolicy { measurables, constraints ->

            val positions = getRelativePositions(
                measurables,
                constraints,
                verticalSpace,
                horizontalSpace,
                maxLinesWhenHidden,
                isExpanded,
                buttonsAdded
            )

            if (amountVisibleElementsWhenHidden != null && !isExpanded) {
                amountVisibleElementsWhenHidden(positions.size - 1)
            }

            var width = constraints.minWidth
            var height = constraints.minHeight

            if (positions.isNotEmpty()) {
                width = maxOf(positions.maxOf { it.maxXCoordinate }, constraints.minWidth)
                height = minOf(
                    maxOf(positions.maxOf { it.maxYCoordinate }, constraints.minHeight),
                    constraints.maxHeight
                )
                //todo    Если высчитать height так как внизу, почему то не все элементы влезают,
                // если ниже есть еще что то(набор тестовых кнопок например), что за фигня,
                // при верхнем варианте всё работает как надо, но не понятно как

//                height = maxOf(positions.maxOf { it.maxYCoordinate }, constraints.minHeight)
            }

            layout(width, height) {
                for ((placeable, dx, dy) in positions) {
                    placeable.placeRelative(dx, dy)
                }
            }
        }
    }
}

private fun MeasureScope.getRelativePositions(
    measurables: List<Measurable>,
    constraints: Constraints,
    verticalSpace: Dp,
    horizontalSpace: Dp,
    maxLinesWhenHidden: Int,
    isExpanded: Boolean,
    buttonsAdded: Boolean
): List<PlaceablePosition> {
    val result = mutableListOf<PlaceablePosition>()
    var x = 0
    var y = 0
    var maxHeight = -1
    var busyLinesCounter = 0

    if (measurables.size <= if (buttonsAdded) 1 else 0) {
        return result
    }

    for ((index, measurable) in measurables.withIndex()) {
        val placeable = measurable.measure(constraints)

        if (placeable.width + x > constraints.maxWidth) {
            busyLinesCounter += 1
            x = 0
            y += maxHeight + verticalSpace.roundToPx()
            maxHeight = -1

            if (buttonsAdded && index == measurables.lastIndex && busyLinesCounter <= maxLinesWhenHidden) {
                return result
            }
        }

        if (buttonsAdded && index == measurables.lastIndex && busyLinesCounter < maxLinesWhenHidden) {
            return result
        }

        if (!isExpanded && busyLinesCounter == maxLinesWhenHidden) {
            result.add(PlaceablePosition(measurables.last().measure(constraints), x, y))
            return result
        }

        result.add(PlaceablePosition(placeable, x, y))
        x += placeable.width + horizontalSpace.roundToPx()
        maxHeight = maxOf(maxHeight, placeable.height)
    }

    return result
}

private data class PlaceablePosition(
    val placeable: Placeable,
    val dx: Int,
    val dy: Int
)

private val PlaceablePosition.maxXCoordinate: Int
    get() = dx + placeable.width

private val PlaceablePosition.maxYCoordinate: Int
    get() = dy + placeable.height