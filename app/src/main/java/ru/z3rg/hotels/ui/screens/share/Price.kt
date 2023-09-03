package ru.z3rg.hotels.ui.screens.share

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.z3rg.hotels.ui.theme.GrayText
import java.text.NumberFormat

@Preview(backgroundColor = 0xFFFEFEFE, showBackground = true)
@Composable
fun PricePreview() {
    Price(
        modifier = Modifier
            .fillMaxWidth(),
        textPrice = 134268,
        textForWhat = "За тур с перелётом"
    )
}

@Composable
fun Price(
    modifier: Modifier,
    textPrePrice: String = "",
    textPrice: Int,
    textForWhat: String
) {
    val numberFormat = NumberFormat.getNumberInstance()
    numberFormat.maximumFractionDigits = 0

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "$textPrePrice${numberFormat.format(textPrice)} ₽",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            text = textForWhat.lowercase(),
            style = TextStyle(
                fontSize = 16.sp,
                color = GrayText
            )
        )
    }
}