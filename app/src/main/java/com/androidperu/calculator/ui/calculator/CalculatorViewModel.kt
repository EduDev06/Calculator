package com.androidperu.calculator.ui.calculator

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context

class CalculatorViewModel: ViewModel() {

    private val _inputText = mutableStateOf(CalculatorState())
    val inputText: State<CalculatorState> = _inputText

    private val _outputText = mutableStateOf(CalculatorState())
    val outputText: State<CalculatorState> = _outputText

    fun onEvent(e: CalculatorEvent) {
        when (e) {
            CalculatorEvent.AllClear -> allClear()
            CalculatorEvent.BackSpace -> backSpace()
            CalculatorEvent.Calculate -> calculate()
            is CalculatorEvent.Write -> write(e.value)
        }
    }

    private fun allClear() {
        _inputText.value = inputText.value.copy(
            text = ""
        )
        _outputText.value = outputText.value.copy(
            text = ""
        )
    }

    private fun backSpace() {
        val length = _inputText.value.text.length
        if (length > 0) {
            _inputText.value = inputText.value.copy(
                text = inputText.value.text.subSequence(0, length - 1) as String
            )
        }
    }

    private fun calculate() {
        val result = rhinoSetUp(_inputText.value.text)
        _outputText.value = outputText.value.copy(
            text = result
        )
    }

    private fun write(value: String) {
        _inputText.value = inputText.value.copy(
            text = inputText.value.text + value
        )
    }

    private fun rhinoSetUp(input: String): String {
        val rhino = Context.enter()
        rhino.optimizationLevel = -1

        val scriptable = rhino.initStandardObjects()
        return rhino.evaluateString(
            scriptable,
            input.replace("×","*"),
            "javascript",
            1,
            null
        ).toString()
    }
}