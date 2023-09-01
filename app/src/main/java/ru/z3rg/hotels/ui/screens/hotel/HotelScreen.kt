package ru.z3rg.hotels.ui.screens.hotel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

@Preview(backgroundColor = 0xFF26269B, showBackground = true)
@Composable
fun HotelScreen() {

    val tagsArray = arrayOf(
        "3-я линия",
        "Платный Wi-Fi в фойе",
        "30 км до аэропорта",
        "1 км до пляжа"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Отель",
                        style = TextStyle(
                            fontSize = 21.sp
                        )
                    )
                }
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(258.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.hotel_paceholder),
                    contentDescription = "Hotel Image"
                )
                Row(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 8.dp)
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
                        .padding(bottom = 8.dp),
                    text = "Steigenberger Makadi",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    text = "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = HyperText
                    )
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 19.dp),
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
                .padding(top = 8.dp)
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
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    columns = GridCells.Fixed(2),
                    content = {
                        items(tagsArray) {
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
                )
                Text(
                    text = "Отель VIP-класса с собственными гольф полями. Высокий уровнь сервиса. Рекомендуем для респектабельного отдыха. Отель принимает гостей от 18 лет!",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )

            }
        }
    }
}