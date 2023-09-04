package ru.z3rg.hotels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.z3rg.hotels.ui.screens.booking.BookingScreen
import ru.z3rg.hotels.ui.screens.hotel.HotelScreen
import ru.z3rg.hotels.ui.screens.listroom.ListRoomScreenPreview
import ru.z3rg.hotels.ui.screens.success.SuccessScreen
import ru.z3rg.hotels.ui.theme.BackGray
import ru.z3rg.hotels.ui.theme.HotelsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotelsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = BackGray) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "hotel") {
                        composable(route = "hotel") {
                            HotelScreen(
                                onRoomSelectClick = {
                                    navController.navigate(route = "list-room")
                                }
                            )
                        }
                        composable(route = "list-room") {
                            ListRoomScreenPreview(
                                onRoomSelectClick = {
                                    navController.navigate(route = "booking")
                                }
                            )
                        }
                        composable(route = "booking") {
                            BookingScreen(
                                onCheckoutClick = {
                                    navController.navigate(route = "success")
                                }
                            )
                        }
                        composable(route = "success") {
                            SuccessScreen()
                        }
                    }
                }
            }
        }
    }
}