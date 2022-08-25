package com.dali.calculator.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class ReplacementTypography(
    val pageTitleTextStyle1: TextStyle,
    val pageTitleTextStyle2: TextStyle,
)

val LocalReplacementTypography = staticCompositionLocalOf {
    ReplacementTypography(
        pageTitleTextStyle1 = TextStyle.Default,
        pageTitleTextStyle2 = TextStyle.Default,
    )
}

internal val replacementTypography = ReplacementTypography(
    pageTitleTextStyle1 = TextStyle(fontSize = 16.sp,),
    pageTitleTextStyle2 = TextStyle(fontSize = 14.sp,fontWeight = FontWeight.W400),
)