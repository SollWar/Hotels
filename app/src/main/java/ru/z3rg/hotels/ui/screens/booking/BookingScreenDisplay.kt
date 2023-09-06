package ru.z3rg.hotels.ui.screens.booking

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.z3rg.domain.models.Booking
import ru.z3rg.domain.models.TouristData
import ru.z3rg.hotels.ui.screens.booking.models.BookingScreenEvent
import ru.z3rg.hotels.ui.screens.booking.models.BookingScreenState
import ru.z3rg.hotels.ui.screens.share.*
import ru.z3rg.hotels.ui.screens.utils.MaskVisualTransformation
import ru.z3rg.hotels.ui.theme.Blue
import ru.z3rg.hotels.ui.theme.Blue10
import ru.z3rg.hotels.ui.theme.GrayText
import java.text.NumberFormat
import kotlin.math.roundToInt


@Preview(backgroundColor = 0xFF26269B, showBackground = true, device = "spec:width=1080px,height=4600px,dpi=440")
@Composable
fun BookingScreenDisplayPreview() {
    BookingScreenDisplay(
        state = BookingScreenState.Display(
            booking = Booking(
                id = 1,
                hotelName = "Лучший пятизвездочный отель в Хургаде, Египет",
                hotelAddress = "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
                rating = 5,
                ratingName = "Превосходно",
                departure = "Москва",
                arrivalCountry = "Египет, Хургада",
                tourDateStart = "19.09.2023",
                tourDateStop = "27.09.2023",
                numberOfNights = 7,
                room = "Люкс номер с видом на море",
                nutrition = "Все включено",
                tourPrice = 289400,
                fuelCharge = 9300,
                serviceCharge = 2150
            ),
            tourist = remember { mutableStateListOf(TouristData()) }
        ),
        onCheckoutClick = {},
        onBackClick = {},
        onEvent = {}
    )
}

const val NUMBER_MASK = "+7 (###) ###-##-##"
const val NUMBER_LENGTH = 10
const val DATE_MASK = "##.##.####"
const val DATE_LENGTH = 8

@Composable
fun BookingScreenDisplay(
    state: BookingScreenState.Display,
    onCheckoutClick: () -> Unit,
    onBackClick: () -> Unit,
    onEvent: (BookingScreenEvent) -> Unit
) {
    var openDialog by remember { mutableStateOf(false)  }
    var alertText by remember { mutableStateOf("") }
    var alertDescription by remember { mutableStateOf("") }

    Box {
        LazyTopBar(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter),
            text = "Бронирование",
            onButtonClick = onBackClick
        )
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()
        var scrollAddPosition by remember { mutableFloatStateOf(0F) }
        Column(
            modifier = Modifier
                .padding(top = 66.dp, bottom = 78.dp)
                .verticalScroll(scrollState)
        ) {
            Description(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White),
                rating = state.booking.rating,
                ratingName = state.booking.ratingName,
                address = state.booking.hotelAddress,
                name = state.booking.hotelName
            )
            TotalRoom(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxWidth(),
                departure = state.booking.departure,
                arrivalCountry = state.booking.arrivalCountry,
                tourDateStart = state.booking.tourDateStart,
                tourDateStop = state.booking.tourDateStop,
                numberOfNights = state.booking.numberOfNights,
                hotelName = state.booking.hotelName,
                room = state.booking.room,
                nutrition = state.booking.nutrition
            )
            CustomerInfo(
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White),
                state,
                onEvent
            )
            Column(
                modifier = Modifier
                    .animateContentSize()
            ) {
                state.tourist.forEachIndexed { index, it ->
                    key(it) {
                        Tourist(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White),
                            onDeleteClick = {
                                state.tourist.remove(it)
                            },
                            onEvent = onEvent,
                            state = state,
                            index = index,
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
                    state.tourist.add(TouristData())
                    coroutineScope.launch {
                        delay(20)
                        scrollState.animateScrollTo(scrollAddPosition.roundToInt())
                    }
                }
            )
            ToPay(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxWidth(),
                tourPrice = state.booking.tourPrice,
                fuelCharge = state.booking.fuelCharge,
                serviceCharge = state.booking.serviceCharge
            )
        }
        LazyBottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = "Оплатить",
            onButtonClick = {
                if (state.tourist.isEmpty()) {
                    alertText = "Нет туристов"
                    alertDescription = "Добавьте хотя бы одного туриста"
                    openDialog = !openDialog
                } else if (!(state.tourist.all {it.isValidData()})
                    || state.email.isEmpty() || state.phone.isEmpty()) {
                    alertText = "Не все поля заполнены"
                    alertDescription = "Заполните обязательные поля чтобы продолжить"
                    openDialog = !openDialog
                } else {
                    onCheckoutClick()
                }
            }
        )
    }
    if (openDialog) {
        AlertDialog(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = alertText,
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            },
            text = {
                Text(
                    text = alertDescription,
                    style = TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
            },
            onDismissRequest = {
                openDialog = !openDialog
            },
            confirmButton = {
                LazyButton(
                    text = "Назад",
                    onButtonClick = {
                        openDialog = !openDialog
                    }
                )
            }
        )
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

fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}

@Composable
fun CustomerInfo(
    modifier: Modifier,
    state: BookingScreenState.Display,
    onEvent: (BookingScreenEvent) -> Unit
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
            value = state.phone,
            isError = (state.phone.length != 10),
            labelText = "Номер телефона",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            onValueChange = {
                if (it.length <= NUMBER_LENGTH) {
                    onEvent(BookingScreenEvent.PhoneUpdate(it))
                }
            },
            visualTransformation = MaskVisualTransformation(NUMBER_MASK)
        )
        LazyTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            value = state.email,
            isError = !isValidEmail(state.email),
            labelText = "Почта",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            onValueChange = {
                onEvent(BookingScreenEvent.EmailUpdate(it))
            }
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
    onEvent: (BookingScreenEvent) -> Unit,
    touristNumber: String,
    state: BookingScreenState.Display,
    index: Int
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
                    value = state.tourist[index].firstName,
                    isError = state.tourist[index].firstName.isEmpty(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true
                    ),
                    onValueChange = {
                        onEvent(BookingScreenEvent.TouristEvent.UpdateFirstName(index, it))
                    }
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Фамилия",
                    value = state.tourist[index].secondName,
                    isError = state.tourist[index].secondName.isEmpty(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true
                    ),
                    onValueChange = {
                        onEvent(BookingScreenEvent.TouristEvent.UpdateSecondName(index, it))
                    }
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Дата рождения",
                    value = state.tourist[index].dateOfBirth,
                    isError = state.tourist[index].dateOfBirth.length < 8,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    visualTransformation = MaskVisualTransformation(DATE_MASK),
                    onValueChange = {
                        if (it.length <= DATE_LENGTH) {
                            onEvent(BookingScreenEvent.TouristEvent.UpdateDateOfBirth(index, it))
                        }
                    }
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Гражданство",
                    value = state.tourist[index].nationality,
                    isError = state.tourist[index].nationality.isEmpty(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true
                    ),
                    onValueChange = {
                        onEvent(BookingScreenEvent.TouristEvent.UpdateNationality(index, it))
                    }
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    labelText = "Номер загранпаспорта",
                    value = state.tourist[index].pasNumber,
                    isError = state.tourist[index].pasNumber.isEmpty(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = {
                        onEvent(BookingScreenEvent.TouristEvent.UpdatePasNumber(index, it))
                    }
                )
                LazyTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    labelText = "Срок действия загранпаспорта",
                    value = state.tourist[index].pasValidPeriod,
                    isError = state.tourist[index].pasValidPeriod.length < 8,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    visualTransformation = MaskVisualTransformation(DATE_MASK),
                    onValueChange = {
                        if (it.length <= DATE_LENGTH) {
                            onEvent(BookingScreenEvent.TouristEvent.UpdatePasValidPeriod(index, it))
                        }
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
fun ToPay(
    modifier: Modifier = Modifier,
    tourPrice: Int,
    fuelCharge: Int,
    serviceCharge: Int
) {
    val numberFormat = NumberFormat.getNumberInstance()
    numberFormat.maximumFractionDigits = 0

    val sum = tourPrice + fuelCharge + serviceCharge

    Column(
        modifier = modifier
    ) {
        PaySum(
            modifier = modifier
                .padding(top = 16.dp),
            text = "Тур",
            textSum = "${numberFormat.format(tourPrice)} ₽",
            itTotal = false
        )
        PaySum(
            modifier = modifier
                .padding(top = 16.dp),
            text = "Топливный сбор",
            textSum = "${numberFormat.format(fuelCharge)} ₽",
            itTotal = false
        )
        PaySum(
            modifier = modifier
                .padding(top = 16.dp),
            text = "Сервисный сбор",
            textSum = "${numberFormat.format(serviceCharge)} ₽",
            itTotal = false
        )
        PaySum(
            modifier = modifier
                .padding(top = 16.dp, bottom = 16.dp),
            text = "К оплате",
            textSum = "${numberFormat.format(sum)} ₽",
            itTotal = true
        )
    }
}

@Composable
fun PaySum(
    modifier: Modifier,
    text: String,
    textSum: String,
    itTotal: Boolean
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                color = GrayText
            )
        )
        Text(
            modifier = Modifier
                .padding(end = 16.dp),
            text = textSum,
            style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.End,
                color = if (itTotal) Blue else Color.Black
            )
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