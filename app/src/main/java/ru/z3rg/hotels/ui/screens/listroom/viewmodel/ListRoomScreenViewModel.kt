package ru.z3rg.hotels.ui.screens.listroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.z3rg.domain.models.Result
import ru.z3rg.domain.usecases.GetRoomListUseCase
import ru.z3rg.hotels.ui.screens.listroom.models.ListRoomScreenEvent
import ru.z3rg.hotels.ui.screens.listroom.models.ListRoomScreenState
import javax.inject.Inject

@HiltViewModel
class ListRoomScreenViewModel @Inject constructor(
    private val getRoomListUseCase: GetRoomListUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<ListRoomScreenState> = MutableStateFlow(ListRoomScreenState.Loading)
    val state = _state.asStateFlow()

    private var _initialized = false

    private fun loadRoomListResult() {
        viewModelScope.launch {
            when (val roomListResult = getRoomListUseCase.invoke()) {
                is Result.Success -> {
                    _state.value = ListRoomScreenState.Display(
                        listRoom = roomListResult.body
                    )
                }
                is Result.Error -> {
                    _state.value = ListRoomScreenState.Error
                }
            }
        }
    }

    fun onEvent(roomScreenEvent: ListRoomScreenEvent) {
        when (roomScreenEvent) {
            is ListRoomScreenEvent.Reload -> {
                _state.value = ListRoomScreenState.Loading
                loadRoomListResult()
            }
            is ListRoomScreenEvent.Enter -> {
                if (!_initialized) {
                    loadRoomListResult()
                    _initialized = true
                }
            }
        }
    }
}