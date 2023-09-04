package ru.z3rg.hotels.ui.screens.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun LazyBottomBar(
    modifier: Modifier = Modifier,
    text: String = "",
    onButtonClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(Color.White)
    ) {
        LazyButton(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp),
            text = text,
            onButtonClick = {
                onButtonClick()
            }
        )
    }
}