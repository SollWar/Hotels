package ru.z3rg.hotels.ui.screens.share

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.z3rg.hotels.R
import ru.z3rg.hotels.ui.theme.BackYellow
import ru.z3rg.hotels.ui.theme.HyperText
import ru.z3rg.hotels.ui.theme.Yellow

@Preview(backgroundColor = 0xFFFEFEFE, showBackground = true)
@Composable
fun DescriptionPreview() {
    Description(
        rating = 5,
        ratingName = "Превосходно",
        address = "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
        name = "Лучший пятизвездочный отель в Хургаде, Египет"
    )
}

@Composable
fun Description(
    modifier: Modifier = Modifier,
    onClickable: () -> Unit = {},
    rating: Int,
    ratingName: String,
    address: String,
    name: String
) {
    Column(
        modifier = modifier
    ) {
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
                text = "$rating $ratingName",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Yellow
                )
            )
        }
        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            text = name,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .clickable {
                    onClickable()
                },
            text = address,
            style = TextStyle(
                fontSize = 14.sp,
                color = HyperText
            )
        )
    }
}