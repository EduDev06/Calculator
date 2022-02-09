package com.androidperu.calculator.ui.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.androidperu.calculator.ui.calculator.CalculatorEvent
import com.androidperu.calculator.ui.calculator.utils.isNumber
import com.androidperu.calculator.ui.calculator.utils.sp
import com.androidperu.calculator.ui.theme.CalculatorTheme

val symbolsList = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "×",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "0", ".", "◄", "="
)

@Composable
fun Panel(
    modifier: Modifier = Modifier,
    height: Dp,
    width: Dp, 
    onEvent: (CalculatorEvent) -> Unit
) {
    Box {
        val size = (height / 5 ) - 10.dp
        val widthButton = (width / 4) - 6.75.dp

        LazyColumn(
            modifier = modifier.width(width)
        ) {
            items(items = symbolsList.chunked(4)) { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    for (text in rowItems) {
                        ContentButton(
                            text = text,
                            size = size,
                            width = widthButton,
                            onEvent = { onEvent(it) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun ContentButton(
    text: String,
    size: Dp,
    width: Dp,
    onEvent: (CalculatorEvent) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .width(width)
            .clickable {
                when (text) {
                    "C" -> onEvent(CalculatorEvent.AllClear)
                    "◄" -> onEvent(CalculatorEvent.BackSpace)
                    "=" -> onEvent(CalculatorEvent.Calculate)
                    else -> onEvent(CalculatorEvent.Write(text))
                }
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size)
                .background(if (isNumber(text)) Color(0xFFEDE7F6) else Color(0xFFFF8A65))
        ) {
            Text(
                text = text,
                color = if (isNumber(text)) Color(0xFF616161) else Color(0xFFFFFFFF),
                style = TextStyle(
                    fontSize = size.sp() / 3
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPanel() {
    CalculatorTheme {
        Panel(
            height = 720.dp,
            width = 360.dp,
            onEvent = { }
        )
    }
}