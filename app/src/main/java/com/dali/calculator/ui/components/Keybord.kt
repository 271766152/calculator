package com.dali.calculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dali.calculator.ui.data.*

@Composable
fun KeyBoard(keyBoardType: KeyBoardType, onClick: ((keyInfo: KeyInfo) -> Unit)) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        keyBoardType.keyboardValue().forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                for (keyInfo in it) {
                    KeyButton(
                        keyInfo, onClick = onClick, modifier = Modifier
                            .weight(if ((keyBoardType is Normal) && keyInfo.action == KeyAction.EQUAL || keyInfo.action == KeyAction.CLEAR) 2f else 1f)
                            .aspectRatio(if ((keyBoardType is Normal) && keyInfo.action == KeyAction.EQUAL || keyInfo.action == KeyAction.CLEAR) 2f else 1f)
                    )
                }
            }
        }
    }
}

@Composable
fun KeyButton(
    keyInfo: KeyInfo,
    modifier: Modifier = Modifier,
    onClick: ((keyInfo: KeyInfo) -> Unit)
) {
    if (keyInfo.keyType==KeyType.NONE){
        Box(modifier = Modifier.then(modifier))
    }else{
        Box(
            modifier = Modifier
                .then(modifier)
                .padding(16.dp)
                .clip(CircleShape)
                .background(keyInfo.color)
                .clickable { onClick.invoke(keyInfo) },
            contentAlignment = Alignment.Center
        ) {
            Text(keyInfo.name, style = TextStyle(fontSize = 22.sp))
        }
    }

}

