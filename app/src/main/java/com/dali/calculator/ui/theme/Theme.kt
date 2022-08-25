package com.dali.calculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

@Composable
fun ReplacementTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val replacementColor = if (darkTheme) {
        replacementDarkColor
    } else {
        replacementLightColor
    }
    CompositionLocalProvider(
        LocalReplacementTypography provides replacementTypography,
        LocalReplacementColor provides replacementColor,
        content = {
            MaterialTheme(
                content = {
                    // A surface container using the 'background' color from the theme
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background, content = content)
                }
            )
        })
}