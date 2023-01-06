package com.example.labels

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
fun LabelDeletable(
    text: String,
    isDeletable: Boolean = true,
    onClickDelete: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .background(
                color = Color(
                    red = 218,
                    green = 218,
                    blue = 218
                ),
                shape = RoundedCornerShape(5.dp)
            )
            .wrapContentSize(align = Alignment.TopStart),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    top = 7.dp,
                    end = 9.dp,
                    bottom = 10.dp
                )
                .weight(weight = 1f, fill = false),
            fontSize = 14.sp,
            fontFamily = ServiceLocator.fontRoboto,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (isDeletable) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_close_clear_cancel),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .padding(end = 9.dp)
                    .clickable(onClick = onClickDelete)
                    .size(16.dp)
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_close_clear_cancel),
                contentDescription = null,
                tint = Color(0, 0, 0, 0x50),
                modifier = Modifier
                    .padding(end = 9.dp)
                    .size(16.dp)
            )
        }
    }
}

@Composable
fun Label(
    text: String,
    onClick: () -> Unit = {}
) {
    Text(
        text = text,
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
            )
            .clickable(onClick = onClick),
        fontSize = 14.sp,
        fontFamily = ServiceLocator.fontRoboto,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}