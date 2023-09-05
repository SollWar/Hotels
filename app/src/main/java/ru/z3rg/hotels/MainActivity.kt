package ru.z3rg.hotels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.z3rg.hotels.ui.screens.booking.BookingScreen
import ru.z3rg.hotels.ui.screens.booking.viewmodel.BookingScreenViewModel
import ru.z3rg.hotels.ui.screens.hotel.HotelScreen
import ru.z3rg.hotels.ui.screens.hotel.viewmodel.HotelScreenViewModel
import ru.z3rg.hotels.ui.screens.listroom.ListRoomScreen
import ru.z3rg.hotels.ui.screens.listroom.viewmodel.ListRoomScreenViewModel
import ru.z3rg.hotels.ui.screens.success.SuccessScreen
import ru.z3rg.hotels.ui.screens.success.viewmodel.SuccessScreenViewModel
import ru.z3rg.hotels.ui.theme.BackGray
import ru.z3rg.hotels.ui.theme.HotelsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotelsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = BackGray) {
                    val navController = rememberNavController()
                    val hotelScreenViewModel: HotelScreenViewModel = hiltViewModel()
                    val bookingScreenViewModel: BookingScreenViewModel = hiltViewModel()
                    val listRoomScreenViewModel: ListRoomScreenViewModel = hiltViewModel()
                    val successScreenViewModel: SuccessScreenViewModel = hiltViewModel()
                    
                    NavHost(navController = navController, startDestination = "hotel") {
                        composable(route = "hotel") {
                            HotelScreen(
                                viewModel = hotelScreenViewModel,
                                onRoomSelectClick = {
                                    navController.navigate(route = "list-room")
                                },
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(route = "list-room") {
                            ListRoomScreen(
                                viewModel = listRoomScreenViewModel,
                                onRoomSelectClick = {
                                    navController.navigate(route = "booking")
                                },
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(route = "booking") {
                            BookingScreen(
                                viewModel = bookingScreenViewModel,
                                onCheckoutClick = {
                                    navController.navigate(route = "success")
                                },
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(route = "success") {
                            SuccessScreen(
                                viewModel = successScreenViewModel,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSuccessClick = {
                                    navController.navigate(route = "hotel") {
                                        popUpTo(route = "hotel") {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}