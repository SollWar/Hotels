package ru.z3rg.hotels.ui.screens.success.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.z3rg.hotels.ui.screens.success.models.SuccessScreenEvent
import ru.z3rg.hotels.ui.screens.success.models.SuccessScreenState
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SuccessScreenViewModel @Inject constructor(): ViewModel() {
    private val _state: MutableStateFlow<SuccessScreenState> = MutableStateFlow(SuccessScreenState.Display(0))
    val state = _state.asStateFlow()

    private var _initialized = false

    private fun generateNumber() {
        _state.value = SuccessScreenState.Display(Random.nextInt(100000, 999999))
    }

    fun onEvent(successScreenEvent: SuccessScreenEvent) {
        when (successScreenEvent) {
            SuccessScreenEvent.Enter -> {
                if (!_initialized) {
                    generateNumber()
                    _initialized = true
                }
            }
        }
    }
}