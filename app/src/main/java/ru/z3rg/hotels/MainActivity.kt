package ru.z3rg.hotels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.z3rg.hotels.ui.screens.hotel.HotelScreen
import ru.z3rg.hotels.ui.screens.listroom.ListRoomScreenPreview
import ru.z3rg.hotels.ui.theme.BackGray
import ru.z3rg.hotels.ui.theme.HotelsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotelsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = BackGray) {
                    HotelScreen()
                    //ListRoomScreenPreview()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HotelsTheme {
        Greeting("Android")
    }
}