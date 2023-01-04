package com.example.labels

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

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
}