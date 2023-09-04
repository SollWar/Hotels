package ru.z3rg.hotels.ui.screens.booking

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.z3rg.hotels.ui.screens.booking.components.LazyTextField
import ru.z3rg.hotels.ui.screens.share.Description
import ru.z3rg.hotels.ui.theme.Blue
import ru.z3rg.hotels.ui.theme.Blue10
import ru.z3rg.hotels.ui.theme.GrayText
import kotlin.math.roundToInt

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

data class TouristData(
    var firstName: String = "",
    var secondName: String = "",
    var dateOfBirth: String = "",
    var nationality: String = "",
    var pasNumber: String = "",
    var pasValidPeriod: String = ""
)

@Preview(backgroundColor = 0xFF26269B, showBackground = true, device = "spec:width=1080px,height=3800px,dpi=440")
@Composable
fun BookingScreen() {
    val touristList = remember { mutableStateListOf(TouristData()) }

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
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()
        var scrollAddPosition by remember { mutableStateOf(0F) }
        Column(
            modifier = Modifier
                .padding(top = 66.dp, bottom = 8.dp)
                .verticalScroll(scrollState)
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
            Column(
                modifier = Modifier
                    .animateContentSize()
            ) {
                touristList.forEachIndexed { index, it ->
                    key(it) {
                        Tourist(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White),
                            onDeleteClick = {
                                touristList.remove(it)
                            },
                            tourist = it,
                            touristNumber = numberTouristFromIndex(index)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            AddTourist(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .onGloballyPositioned { cord ->
                        scrollAddPosition = cord.positionInParent().y
                    },
                onAddClick = {
                    touristList.add(TouristData())
                    coroutineScope.launch {
                        delay(20)
                        scrollState.animateScrollTo(scrollAddPosition.roundToInt())
                    }
                }
            )
        }
    }
}

fun numberTouristFromIndex(index: Int): String {
    return when (index) {
        0 -> "Первый турист"
        1 -> "Второй турист"
        2 -> "Третий турист"
        3 -> "Четвертый турист"
        4 -> "Пятый турист"
        5 -> "Шестой турист"
        6 -> "Седьмой турист"
        7 -> "Восьмой турист"
        else -> "$index турист"
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
            labelText = "Номер телефона",
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
    onDeleteClick: () -> Unit = {},
    touristNumber: String,
    tourist: TouristData
) {
    var collapse by remember { mutableStateOf(false) }
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
                text = touristNumber,
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
                contentDescription = "Раскрыть",
                tint = Blue
            )
        }
        AnimatedVisibility(!collapse) {
            Column {
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Имя",
                    value = tourist.firstName,
                    onValueChange = {
                        tourist.firstName = it
                    }
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Фамилия",
                    value = tourist.secondName,
                    onValueChange = {
                        tourist.secondName = it
                    }
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Дата рождения",
                    value = tourist.dateOfBirth,
                    onValueChange = {
                        tourist.dateOfBirth = it
                    }
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Гражданство",
                    value = tourist.nationality,
                    onValueChange = {
                        tourist.nationality = it
                    }
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Номер загранпаспорта",
                    value = tourist.pasNumber,
                    onValueChange = {
                        tourist.pasNumber = it
                    }
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    labelText = "Срок действия загранпаспорта",
                    value = tourist.pasValidPeriod,
                    onValueChange = {
                        tourist.pasValidPeriod = it
                    }
                )
                Icon(
                    modifier = Modifier
                        .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .width(32.dp)
                        .height(32.dp)
                        .background(Color.Red)
                        .clickable {
                            onDeleteClick()
                        },
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Удалить",
                    tint = Color.White
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
            tourist = TouristData(),
            touristNumber = "Первый турист"
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