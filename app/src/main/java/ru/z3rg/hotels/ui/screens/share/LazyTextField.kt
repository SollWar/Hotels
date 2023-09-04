package ru.z3rg.hotels.ui.screens.share

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.z3rg.hotels.ui.theme.GrayText
import ru.z3rg.hotels.ui.theme.TextFieldBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String,
    onValueChange: (it: String) -> Unit = {}
) {
    var valueText by remember{ mutableStateOf(value) }
    TextField(
        modifier = modifier
            .height(52.dp)
            .fillMaxWidth(),
        value = valueText,
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 16.sp
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = TextFieldBack,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        label = {
            Text(
                text = labelText,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = GrayText
                )
            )
        },
        onValueChange = {
            valueText = it
            onValueChange(it)
        }
    )
}