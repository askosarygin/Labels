package com.example.labels

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object ServiceLocator {
    val fontRoboto = FontFamily(
        Font(
            resId = R.font.roboto_regular,
            weight = FontWeight(400)
        )
    )

    val fontSFProDisplay = FontFamily(
        Font(
            resId = R.font.sf_pro_display_bold,
            weight = FontWeight(700)
        )
    )

    val textStyleScreenAddLabels = TextStyle(
        fontSize = 14.sp,
        fontFamily = fontRoboto
    )
}