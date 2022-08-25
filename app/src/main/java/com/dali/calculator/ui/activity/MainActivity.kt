package com.dali.calculator.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dali.calculator.ui.components.TopLevelViewModel
import com.dali.calculator.ui.screen.CalculatorScreen
import com.dali.calculator.ui.theme.ReplacementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReplacementTheme {
                TopLevelViewModel {
                    CalculatorScreen()
                }
            }
        }
    }
}



