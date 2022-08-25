package com.dali.calculator.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ReplacementColor(
    private val primaryTextColor: Color,
    private val secondaryTextColor: Color,
    private val majorTextColor: Color,
    private val minorTextColor: Color,
    val highLightRoundButtonNormal: Color,
    val highLightRoundButtonPressed: Color,
    val highLightRoundButtonDisable: Color,
    val highLightRoundButtonContentEnable: Color,
    val highLightRoundButtonContentDisable: Color,
    val commonRoundButtonNormal: Color,
    val commonRoundButtonPressed: Color,
    val commonRoundButtonDisable: Color,
    val commonRoundButtonContentEnable: Color,
    val commonRoundButtonContentDisable: Color,
    val inputFrameBackgroundColor: Color,
    val inputHintTextColor: Color,
    val inputCursorColor:Color,
    val dialogBackgroundColor:Color,
    val divider : Color
)

val LocalReplacementColor = staticCompositionLocalOf {
    ReplacementColor(
        primaryTextColor = Color.Unspecified,
        secondaryTextColor = Color.Unspecified,
        majorTextColor = Color.Unspecified,
        minorTextColor = Color.Unspecified,
        highLightRoundButtonNormal = Color.Unspecified,
        highLightRoundButtonPressed = Color.Unspecified,
        highLightRoundButtonDisable = Color.Unspecified,
        highLightRoundButtonContentEnable = Color.Unspecified,
        highLightRoundButtonContentDisable = Color.Unspecified,
        commonRoundButtonNormal = Color.Unspecified,
        commonRoundButtonPressed = Color.Unspecified,
        commonRoundButtonDisable = Color.Unspecified,
        commonRoundButtonContentEnable = Color.Unspecified,
        commonRoundButtonContentDisable = Color.Unspecified,
        inputFrameBackgroundColor = Color.Unspecified,
        inputHintTextColor = Color.Unspecified,
        inputCursorColor = Color.Unspecified,
        dialogBackgroundColor = Color.Unspecified,
        divider = Color.Unspecified,
    )
}

internal val replacementLightColor = ReplacementColor(
    primaryTextColor = Color(0xFF000000),
    secondaryTextColor = Color(0x99000000),
    majorTextColor = Color(0xCC000000),
    minorTextColor = Color(0x66000000),
    highLightRoundButtonNormal = Color(0xFF0D84FF),
    highLightRoundButtonPressed = Color(0xFF0075ED),
    highLightRoundButtonDisable = Color(0x0F000000),
    highLightRoundButtonContentEnable = Color(0xFFFFFFFF),
    highLightRoundButtonContentDisable = Color(0x4D000000),
    commonRoundButtonNormal = Color(0x0F000000),
    commonRoundButtonPressed = Color(0x29000000),
    commonRoundButtonDisable = Color(0x0F000000),
    commonRoundButtonContentEnable = Color(0xCC000000),
    commonRoundButtonContentDisable = Color(0x4D000000),
    inputFrameBackgroundColor = Color(0x0F000000),
    inputHintTextColor = Color(0x66000000),
    inputCursorColor = Color(0xFF0D84FF),
    dialogBackgroundColor = Color(0xFFFFFFFF),
    divider = Color(0x99000000)
)

internal val replacementDarkColor = ReplacementColor(
    primaryTextColor = Color(0xE6FFFFFF),
    secondaryTextColor = Color(0x80FFFFFF),
    majorTextColor = Color(0xCCFFFFFF),
    minorTextColor = Color(0x80FFFFFF),
    highLightRoundButtonNormal = Color(0xFF0073DD),
    highLightRoundButtonPressed = Color(0xFF147EE0),
    highLightRoundButtonDisable = Color(0x26FFFFFF),
    highLightRoundButtonContentEnable = Color(0xFFFFFFFF),
    highLightRoundButtonContentDisable = Color(0xFFFFFFFF),
    commonRoundButtonNormal = Color(0x26FFFFFF),
    commonRoundButtonPressed = Color(0x36FFFFFF),
    commonRoundButtonDisable = Color(0x26FFFFFF),
    commonRoundButtonContentEnable = Color(0xB3FFFFFF),
    commonRoundButtonContentDisable = Color(0x4DFFFFFF),
    inputFrameBackgroundColor = Color(0x14FFFFFF),
    inputHintTextColor = Color(0x4DFFFFFF),
    inputCursorColor = Color(0xFF0D84FF),
    dialogBackgroundColor = Color(0xFF242424),
    divider = Color(0x99000000)
)