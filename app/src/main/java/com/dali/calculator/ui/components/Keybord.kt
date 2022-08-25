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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyBoard(keyBoardType: KeyBoardType, onClick: ((keyInfo: KeyInfo) -> Unit)) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        keyBoardType.keyboardValue().forEach {
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                for (keyInfo in it) {
                    KeyButton(
                        keyInfo, onClick = onClick, modifier = Modifier
                            .weight(if ((keyBoardType is Normal) && keyInfo.action==KeyAction.EQUAL) 2f else 1f)
                            .aspectRatio(if ((keyBoardType is Normal) && keyInfo.action==KeyAction.EQUAL) 2f else 1f)
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
    Box(
        modifier = Modifier
            .then(modifier)
            .padding(10.dp)
            .clip(CircleShape)
            .background(keyInfo.color)
            .clickable { onClick.invoke(keyInfo) },
        contentAlignment = Alignment.Center
    ) {
        Text(keyInfo.name, style = TextStyle(fontSize = 22.sp))
    }
}

sealed class KeyAction{
    object ADD : KeyAction()
    object MINUS : KeyAction()
    object TIMES : KeyAction()
    object DIVIDED : KeyAction()
    object MOL : KeyAction()
    object BACK : KeyAction()
    object CLEAR : KeyAction()
    object EQUAL : KeyAction()
    object NONE : KeyAction()
}

open class KeyBoardType {
    open fun keyboardValue(): Array<List<KeyInfo>> {
        return normalKeyBord
    }
}

class Normal : KeyBoardType() {
    override fun keyboardValue(): Array<List<KeyInfo>> {
        return normalKeyBord
    }
}

data class KeyInfo(val name: String, val action: KeyAction = KeyAction.NONE, val color: Color = Color(0xE6FFFFFF))

private val normalKeyBord = arrayOf(
    listOf(
        KeyInfo(name = "C", action = KeyAction.CLEAR, color = Color.LightGray),
        KeyInfo(name = "X", action = KeyAction.BACK, color = Color.LightGray),
        KeyInfo(name = "%", action = KeyAction.MOL, color = Color.LightGray),
        KeyInfo(name = "/", action = KeyAction.DIVIDED, color = Color.LightGray),
    ),
    listOf(
        KeyInfo(name = "7"),
        KeyInfo(name = "8"),
        KeyInfo(name = "9"),
        KeyInfo(name = "X", action = KeyAction.TIMES, color = Color.LightGray),
    ),
    listOf(
        KeyInfo(name = "4"),
        KeyInfo(name = "5"),
        KeyInfo(name = "6"),
        KeyInfo(name = "-", action = KeyAction.MINUS, color = Color.LightGray),
    ),
    listOf(
        KeyInfo(name = "1"),
        KeyInfo(name = "2"),
        KeyInfo(name = "3"),
        KeyInfo(name = "+", action = KeyAction.ADD, color = Color.LightGray),
    ),
    listOf(
        KeyInfo(name = "R"),
        KeyInfo(name = "0"),
        KeyInfo(name = "=", action = KeyAction.EQUAL, color = Color.LightGray),
    )
)

