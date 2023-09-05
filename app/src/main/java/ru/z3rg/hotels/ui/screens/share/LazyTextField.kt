package ru.z3rg.hotels.ui.screens.share

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.z3rg.hotels.ui.theme.GrayText
import ru.z3rg.hotels.ui.theme.TextFieldBack
import ru.z3rg.hotels.ui.theme.TextFieldError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    onValueChange: (it: String) -> Unit = {}
) {
    TextField(
        modifier = modifier
            .height(58.dp)
            .fillMaxWidth(),
        value = value,
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 16.sp
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = if(isError) TextFieldError else TextFieldBack,
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
            onValueChange(it)
        },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}