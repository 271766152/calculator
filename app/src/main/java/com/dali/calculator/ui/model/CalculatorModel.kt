package com.dali.calculator.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dali.calculator.ui.data.KeyBoardType
import com.dali.calculator.ui.data.KeyInfo
import com.dali.calculator.ui.data.Normal

class CalculatorViewModel : ViewModel() {

    private val _inputStack = MutableLiveData<ArrayList<KeyInfo>>(arrayListOf())
    private val _displayInput = MutableLiveData<String>("")
    private val _calculatorHistory = MutableLiveData<ArrayList<String>>(arrayListOf())
    private val _keyBoardType = MutableLiveData<KeyBoardType>(Normal())


    val calculatorHistory: LiveData<ArrayList<String>> get() = _calculatorHistory
    val displayInput: LiveData<String> get() = _displayInput
    val keyBoardType: LiveData<KeyBoardType> get() = _keyBoardType

    fun addKeyInfo(keyInfo: KeyInfo) {
        _inputStack.value?.let { it ->
            _keyBoardType.value?.validateOperate(it, keyInfo,
                onBack = {
                    _inputStack.value?.removeLastOrNull()
                    val tempString : StringBuilder = StringBuilder()
                    _inputStack.value?.forEach { keyInfo ->
                        tempString.append(keyInfo.name)
                    }
                    _displayInput.value = tempString.toString()
                }, onClear = {
                    _inputStack.value?.clear()
                    _displayInput.value = ""
                }, onValidate = { validate ->
                    if (validate) {
                        _inputStack.value?.add(keyInfo)
                        _displayInput.value = _displayInput.value + keyInfo.name
                    }
                }, onCalculate = {result,resultKeyInfo ->
                    _calculatorHistory.value?.add(_displayInput.value + resultKeyInfo.name + result)
                    _inputStack.value?.clear()
                    _displayInput.value = ""
                }, onReplaceZero = {replaceKeyInfo ->
                    _inputStack.value?.removeLastOrNull()
                    _inputStack.value?.add(replaceKeyInfo)
                    val tempString : StringBuilder = StringBuilder()
                    _inputStack.value?.forEach { keyInfo ->
                        tempString.append(keyInfo.name)
                    }
                    _displayInput.value = tempString.toString()
                })
        }
    }

}

