package com.androidperu.calculator.ui.calculator.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.androidperu.calculator.ui.calculator.utils.sp
import com.androidperu.calculator.ui.theme.CalculatorTheme

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    inputText: String,
    outputText: String,
    height: Dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            BasicTextField(
                value = inputText,
                onValueChange = { },
                enabled = false,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = height.sp() * 0.1,
                    textAlign = TextAlign.End
                )
            )
            Text(
                text = outputText,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = height.sp() * 0.27
                ),
                softWrap = false,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    CalculatorTheme {
        MainContent(
            inputText = "20+40",
            outputText = "60",
            height = 120.dp
        )
    }
}