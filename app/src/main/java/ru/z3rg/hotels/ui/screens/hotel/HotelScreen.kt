package ru.z3rg.hotels.ui.screens.hotel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.z3rg.hotels.R
import ru.z3rg.hotels.ui.screens.share.*
import ru.z3rg.hotels.ui.theme.BackGray
import ru.z3rg.hotels.ui.theme.GrayText

@Preview(backgroundColor = 0xFF26269B, showBackground = true, device = "spec:width=1080px,height=3000px,dpi=440")
@Composable
fun HotelScreen(
    onRoomSelectClick: () -> Unit = {}
) {

    val tagsArray = listOf(
        "3-я линия",
        "Бесплатный Wifi на всей территории отеля",
        "30 км до аэропорта",
        "1 км до пляжа"
    )

    val images = listOf(
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder)
    )

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .align(alignment = Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Отель",
                style = TextStyle(
                    fontSize = 21.sp
                )
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 58.dp, bottom = 70.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Column {
                    ImagesViewer(
                        images = images
                    )
                    Description(
                        rating = 5,
                        ratingName = "Превосходно",
                        address = "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
                        name = "Лучший пятизвездочный отель в Хургаде, Египет"
                    )
                    Price(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 19.dp),
                        textPrePrice = "от ",
                        textPrice = 134268,
                        textForWhat = "За тур с перелётом"
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 12.dp)
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 16.dp),
                        text = "Об отеле",
                        style = TextStyle(
                            fontSize = 22.sp
                        )
                    )
                    Tags(
                        tagsArray = tagsArray
                    )
                    Text(
                        modifier = Modifier
                            .padding(bottom = 16.dp),
                        text = "Отель VIP-класса с собственными гольф полями. Высокий уровнь сервиса. Рекомендуем для респектабельного отдыха. Отель принимает гостей от 18 лет!",
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .padding(top = 5.dp, bottom = 21.dp)
                            .background(BackGray)
                    ) {
                        Facilities(
                            icon = painterResource(id = R.drawable.emoji_happy),
                            topText = "Удобства",
                            bottomText = "Самое необходимое"
                        )
                        Divider(
                            modifier = Modifier
                                .padding(start = 51.dp, end = 15.dp),
                            color = GrayText
                        )
                        Facilities(
                            icon = painterResource(id = R.drawable.tick_square),
                            topText = "Что включено",
                            bottomText = "Самое необходимое"
                        )
                        Divider(
                            modifier = Modifier
                                .padding(start = 51.dp, end = 15.dp),
                            color = GrayText
                        )
                        Facilities(
                            icon = painterResource(id = R.drawable.close_square),
                            topText = "Что не включено",
                            bottomText = "Самое необходимое"
                        )
                    }
                }
            }
        }
        LazyBottomBar(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter),
            text = "К выбору номера",
            onButtonClick = {
                onRoomSelectClick()
            }
        )
    }
}

@Preview(backgroundColor = 0xFFFEFEFE, showBackground = true)
@Composable
fun FacilitiesPreview(
    icon: Painter = painterResource(id = R.drawable.emoji_happy),
    topText: String = "Удобства",
    bottomText: String = "Самое необходимое"
) {
    Facilities(
        icon = icon,
        topText = topText,
        bottomText = bottomText
    )
}

@Composable
fun Facilities(
    icon: Painter,
    topText: String,
    bottomText: String
) {
    Row(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1.2f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp),
                painter = icon,
                contentDescription = "Удобства"
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = topText,
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = bottomText,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = GrayText
                    )
                )
            }
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Перейти"
        )
    }
}