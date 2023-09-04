package ru.z3rg.hotels.ui.screens.share

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.z3rg.hotels.ui.theme.HyperText

@Preview
@Composable
fun LazyButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onButtonClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = HyperText
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onButtonClick()
        }
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White
            )
        )
    }
}