package com.dali.calculator.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ReplacementColor(
    val backgroundColor : Color,
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val displayTextColor:Color,
    val historyTextColor:Color,
)

val LocalReplacementColor = staticCompositionLocalOf {
    ReplacementColor(
        backgroundColor = Color.Unspecified,
        primaryTextColor = Color.Unspecified,
        secondaryTextColor = Color.Unspecified,
        displayTextColor = Color.Unspecified,
        historyTextColor = Color.Unspecified,
    )
}

internal val replacementLightColor = ReplacementColor(
    backgroundColor = Color(0xFFFFFFFF),
    primaryTextColor = Color(0xFF000000),
    secondaryTextColor = Color(0x99000000),
    displayTextColor = Color(0xFF000000),
    historyTextColor = Color(0x99000000),
)

internal val replacementDarkColor = ReplacementColor(
    backgroundColor = Color(0xFF000000),
    primaryTextColor = Color(0xE6FFFFFF),
    secondaryTextColor = Color(0x80FFFFFF),
    displayTextColor = Color(0xE5FFFFFF),
    historyTextColor = Color(0x80FFFFFF),
)