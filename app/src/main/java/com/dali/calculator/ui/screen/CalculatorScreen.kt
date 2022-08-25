package com.dali.calculator.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dali.calculator.ui.components.*
import com.dali.calculator.ui.model.CalculatorViewModel

@Composable
fun CalculatorScreen(){
    val calculatorViewModel: CalculatorViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)
    val inputText = calculatorViewModel.displayInput.observeAsState()
    val calculatorHistory = calculatorViewModel.calculatorHistory.observeAsState()

    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(0.8f)
            .padding(start = 16.dp, end = 16.dp)){
            calculatorHistory.value?.let { HistoryDisplay(it) }
        }

        Box(modifier = Modifier.height(50.dp).padding(start = 16.dp, end = 16.dp)){
            InputTextDisplay(inputText.value.toString(), modifier = Modifier.fillMaxSize())
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider(  thickness = 1.dp )
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.weight(1f)){
            calculatorViewModel.keyBoardType.value?.let {keyBoardType ->
                KeyBoard(keyBoardType) {
                    calculatorViewModel.addKeyInfo(it)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

}