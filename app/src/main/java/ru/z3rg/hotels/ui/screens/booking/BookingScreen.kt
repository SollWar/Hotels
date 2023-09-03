package ru.z3rg.hotels.ui.screens.booking

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.z3rg.hotels.ui.screens.booking.components.LazyTextField
import ru.z3rg.hotels.ui.screens.share.Description
import ru.z3rg.hotels.ui.theme.Blue
import ru.z3rg.hotels.ui.theme.Blue10
import ru.z3rg.hotels.ui.theme.GrayText

data class BookingItem(
    val hotelName: String,
    val hotelAddress: String,
    val horating: Int,
    val ratingName: String,
    val departure: String,
    val arrivalCountry: String,
    val tourDateStart: String,
    val tourDateStop: String,
    val numberOfNights: Int,
    val room: String,
    val nutrition: String,
    val tourPrice: Int,
    val fuelCharge: Int,
    val serviceCharge: Int
)

class TouristList(
    val list: MutableList<String> = mutableListOf()
)

@Preview(backgroundColor = 0xFF26269B, showBackground = true, device = "spec:width=1080px,height=3800px,dpi=440")
@Composable
fun BookingScreen() {
    var touristList by remember{ mutableStateOf(
        TouristList(
            mutableListOf(
                "Первый турист"
            )
        )
    ) }

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .background(Color.White)
                .align(alignment = Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .width(32.dp)
                        .height(32.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Перейти"
                )
                Text(
                    text = "Бронирование",
                    style = TextStyle(
                        fontSize = 18.sp
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
        Column(
            modifier = Modifier
                .padding(top = 66.dp, bottom = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Description(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White),
                rating = 5,
                ratingName = "Превосходно",
                address = "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
                name = "Лучший пятизвездочный отель в Хургаде, Египет"
            )
            TotalRoom(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxWidth(),
                departure = "Москва",
                arrivalCountry = "Египет, Хургада",
                tourDateStart = "19.09.2023",
                tourDateStop = "27.09.2023",
                numberOfNights = 7,
                hotelName = "Лучший пятизвездочный отель в Хургаде, Египет",
                room = "Люкс номер с видом на море",
                nutrition = "Все включено"
            )
            CustomerInfo(
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            )
            touristList.list.forEach {
                Tourist(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                    textTop = it
                )
            }
            AddTourist(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White),
                onAddClick = {
                    touristList.list.add("Второй турист")
                    touristList = TouristList(
                        list = touristList.list
                    )
                }
            )
        }
    }
}

@Composable
fun CustomerInfo(
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 20.dp),
            text = "Информация о покупателе",
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        )
        LazyTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            value = "+7 (951) 555-99-00",
            labelText = "Номер телефона"
        )
        LazyTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            value = "examplemail.000@mail.ru",
            labelText = "Почта"
        )
        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp),
            text = "Эти данные никому не передаются. После оплаты мы вышли чек на указанный вами номер и почту",
            style = TextStyle(
                fontSize = 14.sp,
                color = GrayText
            )
        )
    }
}

@Composable
fun Tourist(
    modifier: Modifier = Modifier,
    textTop: String
) {
    var collapse by remember{ mutableStateOf(false) }
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = textTop,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Icon(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .width(32.dp)
                    .height(32.dp)
                    .background(Blue10)
                    .clickable {
                        collapse = !collapse
                    },
                imageVector = if (!collapse) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Перейти",
                tint = Blue
            )
        }
        AnimatedVisibility(!collapse) {
            Column {
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Имя",
                    value = "Иван"
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Фамилия",
                    value = "Иванов"
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Дата рождения",
                    value = ""
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Гражданство",
                    value = ""
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Номер загранпаспорта",
                    value = ""
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    labelText = "Срок действия загранпаспорта",
                    value = ""
                )
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFEFEFE, showBackground = true)
@Composable
fun TouristPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Tourist(
            textTop = "Первый турист"
        )
    }
}

@Preview(backgroundColor = 0xFFFEFEFE, showBackground = true)
@Composable
fun CustomerInfoPreview() {
    CustomerInfo(
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun TotalRoom(
    modifier: Modifier = Modifier,
    departure: String,
    arrivalCountry: String,
    tourDateStart: String,
    tourDateStop: String,
    numberOfNights: Int,
    hotelName: String,
    room: String,
    nutrition: String
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            TotalRoomItem(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp),
                textDescription = "Вылет из",
                textItem = departure
            )
            TotalRoomItem(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                textDescription = "Страна, город",
                textItem = arrivalCountry
            )
            TotalRoomItem(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                textDescription = "Даты",
                textItem = "$tourDateStart – $tourDateStop"
            )
            TotalRoomItem(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                textDescription = "Кол-во ночей",
                textItem = "$numberOfNights ночей"
            )
            TotalRoomItem(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                textDescription = "Отель",
                textItem = hotelName
            )
            TotalRoomItem(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                textDescription = "Номер",
                textItem = room
            )
            TotalRoomItem(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                textDescription = "Питание",
                textItem = nutrition
            )
        }
    }
}

@Composable
fun AddTourist(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Добавить туриста",
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Icon(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .width(32.dp)
                .height(32.dp)
                .background(Blue)
                .clickable {
                    onAddClick()
                },
            imageVector = Icons.Default.Add,
            contentDescription = "Перейти",
            tint = Color.White
        )
    }
}


@Composable
fun TotalRoomItem(
    modifier: Modifier,
    textDescription: String,
    textItem: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = textDescription,
            style = TextStyle(
                fontSize = 16.sp,
                color = GrayText
            )
        )
        Text(
            modifier = Modifier
                .weight(1.4f),
            text = textItem,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }
}

@Preview(backgroundColor = 0xFFFEFEFE, showBackground = true)
@Composable
fun TotalRoomPreview() {
    TotalRoom(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        departure = "Москва",
        arrivalCountry = "Египет, Хургада",
        tourDateStart = "19.09.2023",
        tourDateStop = "27.09.2023",
        numberOfNights = 7,
        hotelName = "Лучший пятизвездочный отель в Хургаде, Египет",
        room = "Люкс номер с видом на море",
        nutrition = "Все включено"
    )
}