package com.dali.calculator.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dali.calculator.ui.components.KeyAction
import com.dali.calculator.ui.components.KeyInfo

class CalculatorViewModel  : ViewModel() {

    private val _inputStack = MutableLiveData<ArrayList<KeyInfo>>()
    private val _displayInput = MutableLiveData<String>("")
    private val _calculatorHistory = MutableLiveData<ArrayList<String>>(arrayListOf())
    val calculatorHistory: LiveData<ArrayList<String>> get() = _calculatorHistory

    val inputStack: LiveData<ArrayList<KeyInfo>> get() = _inputStack
    val displayInput: LiveData<String> get() = _displayInput

    fun addKeyInfo(keyInfo: KeyInfo){
        _inputStack.value?.add(keyInfo)
        _displayInput.value = _displayInput.value + keyInfo.name
        if (keyInfo.action==KeyAction.EQUAL){
            _calculatorHistory.value?.add(_displayInput.value + "结果")
            _displayInput.value = ""
        }
    }

}

