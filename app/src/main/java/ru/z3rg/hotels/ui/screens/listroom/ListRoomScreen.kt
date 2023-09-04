package ru.z3rg.hotels.ui.screens.listroom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.z3rg.hotels.R
import ru.z3rg.hotels.ui.screens.share.ImagesViewer
import ru.z3rg.hotels.ui.screens.share.LazyButton
import ru.z3rg.hotels.ui.screens.share.Price
import ru.z3rg.hotels.ui.screens.share.Tags
import ru.z3rg.hotels.ui.theme.Blue
import ru.z3rg.hotels.ui.theme.Blue10


data class RoomItem(
    val id: Int,
    val name: String,
    val price: Int,
    val pricePer: String,
    val peculiarities: List<String>,
    val imageUrls: List<Painter>
)

@Composable
fun ListRoomScreen(
    roomItems: List<RoomItem>,
    onRoomSelectClick: () -> Unit = {}
) {
    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .background(Color.White)
                .align(alignment = Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Steigenberger Makadi",
                style = TextStyle(
                    fontSize = 21.sp
                )
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(top = 58.dp),
            content = {
                items(roomItems) { room ->
                    Column(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .background(Color.White)
                    ) {
                        ImagesViewer(
                            modifier = Modifier
                                .padding(top = 16.dp),
                            images = room.imageUrls
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                            text = room.name,
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Tags(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp)
                                .fillMaxWidth(),
                            tagsArray = room.peculiarities
                        )
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(Blue10)
                                .height(29.dp)
                                .clickable {

                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                text = "Подробнее о номере",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Blue
                                )
                            )
                            Icon(
                                modifier = Modifier
                                    .padding(start = 2.dp, end = 2.dp),
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Перейти",
                                tint = Blue
                            )
                        }
                        Price(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 16.dp),
                            textPrice = room.price,
                            textForWhat = room.pricePer
                        )
                        LazyButton(
                            modifier = Modifier
                                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                            text = "Выбрать номер",
                            onButtonClick = {
                                onRoomSelectClick()
                            }
                        )
                    }
                }
            }
        )
    }
}

@Preview(backgroundColor = 0xFF26269B, showBackground = true, device = "spec:width=1080px,height=3400px,dpi=440")
@Composable
fun ListRoomScreenPreview(
    onRoomSelectClick: () -> Unit = {}
) {
    ListRoomScreen(
        roomItems = listOf(
            RoomItem(
                id = 1,
                name = "Стандартный номер с видом на бассейн",
                price = 186600,
                pricePer = "За 7 ночей с перелетом",
                peculiarities = listOf(
                    "Включен только завтрак",
                    "Кондиционер"
                ),
                imageUrls = listOf(
                    painterResource(id = R.drawable.hotel_paceholder),
                    painterResource(id = R.drawable.hotel_paceholder)
                )
            ),
            RoomItem(
                id = 2,
                name = "Люкс номер с видом на море",
                price = 289400,
                pricePer = "За 7 ночей с перелетом",
                peculiarities = listOf(
                    "Все включено",
                    "Кондиционер",
                    "Собственный бассейн"
                ),
                imageUrls = listOf(
                    painterResource(id = R.drawable.hotel_paceholder),
                    painterResource(id = R.drawable.hotel_paceholder),
                    painterResource(id = R.drawable.hotel_paceholder)
                )
            )
        ),
        onRoomSelectClick = {
            onRoomSelectClick()
        }
    )
}