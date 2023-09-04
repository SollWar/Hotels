package ru.z3rg.hotels.ui.screens.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.z3rg.hotels.R
import ru.z3rg.hotels.ui.screens.share.LazyBottomBar
import ru.z3rg.hotels.ui.screens.share.LazyTopBar
import ru.z3rg.hotels.ui.theme.BackGray
import ru.z3rg.hotels.ui.theme.GrayText

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, device = "spec:width=1080px,height=2400px,dpi=440")
@Composable
fun SuccessScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyTopBar(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter),
            text = "Заказ оплачен"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(BackGray)
                    .width(94.dp)
                    .height(94.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(44.dp)
                        .height(44.dp),
                    painter = painterResource(id = R.drawable.success_icon),
                    contentDescription = "Успех"
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 20.dp),
                text = "Ваш заказ принят в работу",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight =  FontWeight(500),
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = "Подтверждение заказа №104893 может занять некоторое время (от 1 часа до суток). Как только мы получим ответ от туроператора, вам на почту придет уведомление.",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight =  FontWeight(400),
                    textAlign = TextAlign.Center,
                    color = GrayText
                )
            )
        }
        LazyBottomBar(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter),
            text = "Супер!"
        )
    }
}