package ru.z3rg.hotels.ui.screens.success

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import ru.z3rg.hotels.ui.screens.success.models.SuccessScreenEvent
import ru.z3rg.hotels.ui.screens.success.models.SuccessScreenState
import ru.z3rg.hotels.ui.screens.success.viewmodel.SuccessScreenViewModel

@Composable
fun SuccessScreen(
    viewModel: SuccessScreenViewModel,
    onBackClick: () -> Unit,
    onSuccessClick: () -> Unit
) {
    viewModel.onEvent(SuccessScreenEvent.Enter)
    val state = viewModel.state.collectAsState()

    when (state.value) {
        is SuccessScreenState.Display -> {
            SuccessScreenDisplay(
                state = state.value as SuccessScreenState.Display,
                onBackClick = onBackClick,
                onSuccessClick = onSuccessClick
            )
        }
    }
}