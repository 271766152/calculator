package com.dali.calculator.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dali.calculator.ui.model.CalculatorViewModel

@Composable
fun TopLevelViewModel(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val vmStoreOwner = remember(context) { context as ViewModelStoreOwner }
    CompositionLocalProvider( LocalNavGraphViewModelStoreOwner provides vmStoreOwner, content = {
        val calculatorViewModel : CalculatorViewModel = viewModel()
        content.invoke()
    })
}

val LocalNavGraphViewModelStoreOwner =
    staticCompositionLocalOf<ViewModelStoreOwner> {
        TODO("Undefined")
    }