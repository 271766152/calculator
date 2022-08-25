package com.dali.calculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.dali.calculator.ui.theme.LocalReplacementColor

@Composable
fun InputTextDisplay(text: String, modifier : Modifier = Modifier,) {
    BasicTextField(modifier = modifier, value = text, textStyle= TextStyle(fontSize = (22.sp),color = LocalReplacementColor.current.displayTextColor,textAlign = TextAlign.End),onValueChange = {},
        decorationBox = @Composable { innerTextField ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
                // 当空字符时, 显示0
                if (text.isEmpty()) {
                    Text(text = "0", style = TextStyle(fontSize = (22.sp), color = LocalReplacementColor.current.displayTextColor ))
                }

                // 原本输入框的内容
                innerTextField()
            }
        })
}

@Composable
fun HistoryDisplay(historyList: List<String>) {

    LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom) {
        items(historyList) { item ->
            Text(text = item ,style = TextStyle(fontSize = (16.sp), color = LocalReplacementColor.current.historyTextColor ))
        }
    }

}