package ru.z3rg.hotels.ui.screens.booking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import ru.z3rg.hotels.ui.screens.booking.models.BookingScreenEvent
import ru.z3rg.hotels.ui.screens.booking.models.BookingScreenState
import ru.z3rg.hotels.ui.screens.booking.viewmodel.BookingScreenViewModel
import ru.z3rg.hotels.ui.screens.share.screens.ScreenError
import ru.z3rg.hotels.ui.screens.share.screens.ScreenLoading

@Composable
fun BookingScreen(
    viewModel: BookingScreenViewModel,
    onCheckoutClick: () -> Unit,
    onBackClick: () -> Unit,
    onEvent: (BookingScreenEvent) -> Unit
) {
    viewModel.onEvent(BookingScreenEvent.Enter)
    val state = viewModel.state.collectAsState()

    when (state.value) {
        is BookingScreenState.Display -> {
            BookingScreenDisplay(
                state = state.value as BookingScreenState.Display,
                onCheckoutClick = onCheckoutClick,
                onBackClick = onBackClick,
                onEvent = onEvent
            )
        }
        is BookingScreenState.Loading -> {
            ScreenLoading()
        }
        is BookingScreenState.Error -> {
            ScreenError(
                onReloadClick = {
                    viewModel.onEvent(BookingScreenEvent.Reload)
                }
            )
        }
    }
}
