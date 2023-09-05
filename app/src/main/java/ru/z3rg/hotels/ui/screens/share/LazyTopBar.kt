package ru.z3rg.hotels.ui.screens.share

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun LazyTopBar(
    modifier: Modifier = Modifier,
    text: String = "",
    backButton: Boolean = true,
    onButtonClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (backButton) {
                Icon(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clip(CircleShape)
                        .width(32.dp)
                        .height(32.dp)
                        .clickable {
                            onButtonClick()
                        },
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Назад"
                )
            } else {
                Spacer(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(32.dp)
                        .height(32.dp)
                )
            }
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(32.dp)
                    .height(32.dp)
            )
        }
    }
}