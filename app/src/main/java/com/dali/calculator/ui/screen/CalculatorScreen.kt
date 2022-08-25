package com.dali.calculator.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dali.calculator.ui.components.*
import com.dali.calculator.ui.model.CalculatorViewModel

@Composable
fun CalculatorScreen(){
    val context = LocalContext.current
    val calculatorViewModel: CalculatorViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)
    val inputText = calculatorViewModel.displayInput.observeAsState()
    val calculatorHistory = calculatorViewModel.calculatorHistory.observeAsState()

    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth().weight(0.8f)){
            calculatorHistory.value?.let { HistoryDisplay(it) }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)){
            InputTextDisplay(inputText.value.toString())
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)){
            KeyBoard(Normal()) {
                calculatorViewModel.addKeyInfo(it)
            }
        }
    }

}