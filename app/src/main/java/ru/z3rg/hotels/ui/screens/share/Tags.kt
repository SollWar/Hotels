package ru.z3rg.hotels.ui.screens.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.z3rg.hotels.ui.theme.BackGray
import ru.z3rg.hotels.ui.theme.GrayText

@Preview(backgroundColor = 0xFFFEFEFE, showBackground = true)
@Composable
fun TagsPreview(
    tagsArray: List<String> = listOf(
        "3-я линия",
        "Платный Wi-Fi в фойе",
        "30 км до аэропорта",
        "1 км до пляжа"
    )
) {
    Tags(
        modifier = Modifier
            .fillMaxWidth(),
        tagsArray = tagsArray
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Tags(
    modifier: Modifier = Modifier,
    tagsArray: List<String>
) {
    FlowRow(
        modifier = modifier
    ) {
        tagsArray.forEach {
            Box(
                modifier = Modifier
                    .padding(end = 8.dp, bottom = 8.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(BackGray)
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    text = it,
                    style = TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = GrayText
                    ),
                    maxLines = 1
                )
            }
        }
    }
}