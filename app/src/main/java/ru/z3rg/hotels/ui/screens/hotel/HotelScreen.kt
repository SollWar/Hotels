package ru.z3rg.hotels.ui.screens.hotel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import ru.z3rg.hotels.ui.screens.hotel.models.HotelScreenEvent
import ru.z3rg.hotels.ui.screens.hotel.models.HotelScreenState
import ru.z3rg.hotels.ui.screens.hotel.viewmodel.HotelScreenViewModel
import ru.z3rg.hotels.ui.screens.share.screens.ScreenError
import ru.z3rg.hotels.ui.screens.share.screens.ScreenLoading


@Composable
fun HotelScreen(
    viewModel: HotelScreenViewModel,
    onRoomSelectClick: () -> Unit,
    onBackClick: () -> Unit
) {
    viewModel.onEvent(HotelScreenEvent.Enter)
    val state = viewModel.state.collectAsState()

    when (state.value) {
        is HotelScreenState.Display -> {
            HotelScreenDisplay(
                state = state.value as HotelScreenState.Display,
                onRoomSelectClick = onRoomSelectClick,
                onBackClick = onBackClick
            )
        }
        is HotelScreenState.Loading -> {
            ScreenLoading()
        }
        is HotelScreenState.Error -> {
            ScreenError(
                onReloadClick = {
                    viewModel.onEvent(HotelScreenEvent.Reload)
                }
            )
        }
    }
}