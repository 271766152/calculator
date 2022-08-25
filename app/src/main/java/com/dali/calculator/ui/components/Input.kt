package com.dali.calculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun InputTextDisplay(text: String) {
    BasicTextField(value = text, onValueChange = {},
        decorationBox = @Composable { innerTextField ->
            // 当空字符时, 显示0
            if (text.isEmpty()) {
                Text(text = "0")
            }

            // 原本输入框的内容
            innerTextField()
        })
}

@Composable
fun HistoryDisplay(historyList: List<String>) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(historyList) { item ->
            Text(text = item)
        }
    }

}