package com.example.labels

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpandTextButton(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(
                start = 10.dp,
                top = 7.dp,
                end = 9.dp,
                bottom = 10.dp
            ),
        fontFamily = ServiceLocator.fontRoboto,
        color = Color.Blue,
        fontSize = 14.sp
    )
}

@Composable
fun ShrinkTextButton(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(
                start = 10.dp,
                top = 7.dp,
                end = 9.dp,
                bottom = 10.dp
            ),
        fontFamily = ServiceLocator.fontRoboto,
        color = Color.Gray,
        fontSize = 14.sp
    )
}

@Composable
fun LabelDescription(
    textLabelDescription: String,
    modifier: Modifier
) {
    Text(
        text = textLabelDescription,
        fontFamily = ServiceLocator.fontSFProDisplay,
        fontSize = 24.sp,
        modifier = modifier
    )
}

@Composable
fun Label(
    textLabel: String
) {
    Text(
        text = textLabel,
        modifier = Modifier
            .background(
                color = Color(
                    red = 218,
                    green = 218,
                    blue = 218
                ),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(
                start = 10.dp,
                top = 7.dp,
                end = 9.dp,
                bottom = 10.dp
            ),
        fontSize = 14.sp,
        fontFamily = ServiceLocator.fontRoboto,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}