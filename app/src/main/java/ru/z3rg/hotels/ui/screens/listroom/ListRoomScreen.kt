package ru.z3rg.hotels.ui.screens.listroom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import ru.z3rg.hotels.ui.screens.listroom.models.ListRoomScreenEvent
import ru.z3rg.hotels.ui.screens.listroom.models.ListRoomScreenState
import ru.z3rg.hotels.ui.screens.listroom.viewmodel.ListRoomScreenViewModel
import ru.z3rg.hotels.ui.screens.share.screens.ScreenError
import ru.z3rg.hotels.ui.screens.share.screens.ScreenLoading

@Composable
fun ListRoomScreen(
    viewModel: ListRoomScreenViewModel,
    onRoomSelectClick: () -> Unit,
    onBackClick: () -> Unit
) {
    viewModel.onEvent(ListRoomScreenEvent.Enter)
    val state = viewModel.state.collectAsState()

    when (state.value) {
        is ListRoomScreenState.Display -> {
            ListRoomScreenDisplay(
                state = state.value as ListRoomScreenState.Display,
                onRoomSelectClick = onRoomSelectClick,
                onBackClick = onBackClick
            )
        }
        is ListRoomScreenState.Loading -> {
            ScreenLoading()
        }
        is ListRoomScreenState.Error -> {
            ScreenError(
                onReloadClick = {
                    viewModel.onEvent(ListRoomScreenEvent.Reload)
                }
            )
        }
    }
}