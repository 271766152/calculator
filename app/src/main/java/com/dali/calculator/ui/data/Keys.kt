package com.dali.calculator.ui.data

import androidx.compose.ui.graphics.Color
import java.lang.StringBuilder
import java.text.DecimalFormat
import java.util.regex.Pattern

data class KeyInfo(
    val name: String,
    val action: KeyAction = KeyAction.NONE,
    val keyType: KeyType = KeyType.CHARACTER,
    val color: Color = Color(0xFFF7F7F7)
)

sealed class KeyAction {
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

sealed class KeyType {
    object OTHER : KeyType()
    object OPERATE : KeyType()
    object CHARACTER : KeyType()
    object NONE : KeyType()
}

open class KeyBoardType {
    open fun keyboardValue(): Array<List<KeyInfo>> {
        return arrayOf()
    }

    open fun validateOperate(
        inputStack: ArrayList<KeyInfo>,
        keyInfo: KeyInfo,
        onValidate: ((validate: Boolean) -> Unit),
        onReplaceZero: ((keyInfo: KeyInfo) -> Unit),
        onBack: (() -> Unit),
        onClear: (() -> Unit),
        onCalculate: ((result: String,keyInfo: KeyInfo) -> Unit)
    ) {}
}

class Normal : KeyBoardType() {

    private val normalKeyBord = arrayOf(
        listOf(
            KeyInfo(name = "AC", action = KeyAction.CLEAR, keyType = KeyType.OTHER, color = Color(0xFFEAECEE)),
            KeyInfo(name = "D", action = KeyAction.BACK, keyType = KeyType.OTHER, color = Color(0xFFEAECEE)),
            KeyInfo(name = "/", action = KeyAction.DIVIDED, keyType = KeyType.OPERATE, color = Color(0xFFEAECEE)),
        ),
        listOf(
            KeyInfo(name = "7"),
            KeyInfo(name = "8"),
            KeyInfo(name = "9"),
            KeyInfo(name = "*", action = KeyAction.TIMES, keyType = KeyType.OPERATE, color = Color(0xFFEAECEE)),
        ),
        listOf(
            KeyInfo(name = "4"),
            KeyInfo(name = "5"),
            KeyInfo(name = "6"),
            KeyInfo(name = "-", action = KeyAction.MINUS, keyType = KeyType.OPERATE, color = Color(0xFFEAECEE)),
        ),
        listOf(
            KeyInfo(name = "1"),
            KeyInfo(name = "2"),
            KeyInfo(name = "3"),
            KeyInfo(name = "+", action = KeyAction.ADD, keyType = KeyType.OPERATE, color = Color(0xFFEAECEE)),
        ),
        listOf(
            KeyInfo(name = "" , keyType = KeyType.NONE),
            KeyInfo(name = "0"),
            KeyInfo(name = "=", action = KeyAction.EQUAL, keyType = KeyType.OTHER, color = Color(0xFFFFA07A)),
        )
    )

    override fun keyboardValue(): Array<List<KeyInfo>> {
        return normalKeyBord
    }

    override fun validateOperate(
        inputStack: ArrayList<KeyInfo>,
        keyInfo: KeyInfo,
        onValidate: ((validate: Boolean) -> Unit),
        onReplaceZero: ((keyInfo: KeyInfo) -> Unit),
        onBack: (() -> Unit),
        onClear: (() -> Unit),
        onCalculate: ((result: String,keyInfo: KeyInfo) -> Unit)
    ) {
        val inputList = ArrayList(inputStack)

        when (keyInfo.action) {
            KeyAction.BACK -> {
                onBack.invoke()
                return
            }
            KeyAction.CLEAR -> {
                onClear.invoke()
                return
            }
            KeyAction.EQUAL -> {
                onCalculate.invoke(calculator(inputList),keyInfo)
                return
            }

            else -> {
                val lastOperate = inputList.removeLastOrNull()
                if (lastOperate == null) {
                    onValidate.invoke(keyInfo.keyType == KeyType.CHARACTER)
                } else {
                    if (lastOperate.keyType == KeyType.OPERATE && keyInfo.keyType == KeyType.OPERATE) onValidate.invoke(
                        false
                    )
                    if (lastOperate.keyType == KeyType.OPERATE && keyInfo.keyType == KeyType.CHARACTER) onValidate.invoke(
                        true
                    )
                    if (lastOperate.keyType == KeyType.CHARACTER && keyInfo.keyType == KeyType.OPERATE) onValidate.invoke(
                        true
                    )
                    if (lastOperate.keyType == KeyType.CHARACTER && keyInfo.keyType == KeyType.CHARACTER) {
                        if (lastOperate.name != "0") {
                            onValidate.invoke(true)
                        } else {
                            val validateZero = lastNotZero(inputList)
                            onValidate.invoke(validateZero)
                            if (!validateZero) onReplaceZero.invoke(keyInfo)
                        }
                    }
                }
            }
        }
    }

    private fun lastNotZero(inputStack: ArrayList<KeyInfo>): Boolean {
        val inputList = ArrayList(inputStack)
        val lastOperate = inputList.removeLastOrNull()
        if (lastOperate == null) {
            return false
        } else {
            if (lastOperate.keyType != KeyType.CHARACTER) {
                return false
            }
            return if (lastOperate.name == "0") {
                lastNotZero(inputList)
            } else {
                true
            }
        }
    }

    private fun calculator(inputStack: ArrayList<KeyInfo>): String {
        val inputList = ArrayList(inputStack)
        val input = StringBuilder()
        inputList.forEach {
            input.append(it.name)
        }
        return calc(input.toString())
    }


    private fun calc(input: String): String {
        val operate: ArrayList<Char> = operate2op(input)
        val character: ArrayList<Double> = operate2cha(input)

        var i = 0
        while (i < operate.size) {
            val c = operate[i]
            if (c == '*' || c == '/') {
                operate.removeAt(i)
                val opl: Double = character.removeAt(i)
                val opr: Double = character.removeAt(i)
                if (c == '*') {
                    character.add(i, opl * opr)
                } else character.add(i, opl / opr)
                i--
            }
            i++
        }
        while (operate.isNotEmpty()) {
            val o: Char = operate.removeAt(0)
            var chal: Double = character.removeAt(0)
            val chara: Double = character.removeAt(0)
            if (o == '-') {
                chal -= chara
            }
            if (o == '+') {
                chal += chara
            }
            character.add(0, chal)
        }
        return character[0].toString()
    }

    private fun operate2op(operate: String): ArrayList<Char> {
        val operateMinus = changeMinus(operate)
        val regex = "@[0-9]|[0-9]"
        val pt: Pattern = Pattern.compile(regex)
        val split: Array<String> = pt.split(operateMinus)
        val list: ArrayList<Char> = ArrayList()
        for (i in split.indices) {
            val temp = split[i].trim { it <= ' ' }
            if (temp == "+" || temp == "-" || temp == "*" || temp == "/") {
                list.add(temp.trim { it <= ' ' }[0])
            }
        }
        return list
    }


    private fun operate2cha(operate: String): ArrayList<Double> {
        val operateMinus = changeMinus(operate)
        val pt: Pattern = Pattern.compile("\\+|\\-|\\*|\\/")
        val split: Array<String> = pt.split(operateMinus)

        val list: ArrayList<Double> = ArrayList()
        for (i in split.indices) {
            var single = split[i]
            if (single[0] == '@') {
                single = "-" + single.substring(1)
            }
            list.add(single.toDouble())
        }
        return list
    }

    private fun changeMinus(operate: String): String {
        var operateTemp = operate
        for (i in operateTemp.indices) {
            val c = operateTemp[i]
            if (c == '-') {
                if (i == 0) {
                    operateTemp = "@" + operateTemp.substring(1)
                } else {
                    val cprev = operateTemp[i - 1]
                    if (cprev == '+' || cprev == '-' || cprev == '*' || cprev == '/') {
                        operateTemp = operateTemp.substring(0, i) + "@" + operateTemp.substring(i + 1)
                    }
                }
            }
        }
        return operateTemp
    }
}
