package ru.z3rg.hotels.ui.screens.hotel

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.z3rg.hotels.R
import ru.z3rg.hotels.ui.theme.*

@Preview(backgroundColor = 0xFF26269B, showBackground = true, device = "spec:width=1080px,height=3000px,dpi=440")
@Composable
fun HotelScreen() {

    val tagsArray = arrayOf(
        "3-я линия",
        "Бесплатный Wifi на всей территории отеля",
        "30 км до аэропорта",
        "1 км до пляжа"
    )

    val images = arrayOf(
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
                    .background(Color.White)
            ) {
                Column {
                    ImagesViewer(
                        images = images
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .height(30.dp)
                            .background(BackYellow),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 10.dp, top = 7.dp, bottom = 7.dp)
                                .height(15.dp)
                                .width(15.dp),
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "Star",
                            tint = Yellow
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 2.dp, end = 10.dp),
                            text = "5 Превосходно",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Yellow
                            )
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                        text = "Steigenberger Makadi",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        text = "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = HyperText
                        )
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 19.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "от 134 673 ₽",
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp),
                            text = "за тур с перелётом",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = GrayText
                            )
                        )
                    }
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
        Box(
            modifier = Modifier
                .background(Color.White)
                .align(alignment = Alignment.BottomCenter)
        ) {
            Button(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HyperText
                ),
                onClick = {

                }
            ) {
                Text(
                    text = "К выбору номера",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFEFEFE, showBackground = true)
@Composable
fun ImageViewerPreview(
    images: Array<Painter> = arrayOf(
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder)
    )
) {
    ImagesViewer(
        images = images
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesViewer(
    images: Array<Painter>
) {
    val pagerState = rememberPagerState()

    Box {
        HorizontalPager(
            pageCount = images.size,
            state = pagerState
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .height(258.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
                painter = images[it],
                contentDescription = "Hotel Image"
            )
        }
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color.White)
                .height(17.dp)
                .align(alignment = Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            ) {
                repeat(images.size) {
                    val color = if (pagerState.currentPage == it) Color.Black else GrayText
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 5.dp)
                            .clip(CircleShape)
                            .background(color)
                            .width(7.dp)
                            .height(7.dp)
                    )
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFEFEFE, showBackground = true)
@Composable
fun TagsPreview(
    tagsArray: Array<String> = arrayOf(
        "3-я линия",
        "Платный Wi-Fi в фойе",
        "30 км до аэропорта",
        "1 км до пляжа"
    )
) {
    Tags(tagsArray = tagsArray)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Tags(
    tagsArray: Array<String>
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        tagsArray.forEach {
            Box(
                modifier = Modifier
                    .padding(end = 8.dp, bottom = 8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .background(BackGray)
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
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Перейти"
        )
    }
}