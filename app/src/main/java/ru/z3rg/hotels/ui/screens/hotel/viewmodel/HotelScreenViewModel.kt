package ru.z3rg.hotels.ui.screens.hotel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.z3rg.domain.models.Result
import ru.z3rg.domain.usecases.GetHotelDataUseCase
import ru.z3rg.hotels.ui.screens.hotel.models.HotelScreenEvent
import ru.z3rg.hotels.ui.screens.hotel.models.HotelScreenState
import javax.inject.Inject

@HiltViewModel
class HotelScreenViewModel @Inject constructor(
    private val getHotelDataUseCase: GetHotelDataUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<HotelScreenState> = MutableStateFlow(HotelScreenState.Loading)
    val state = _state.asStateFlow()

    private var _initialized = false

    private fun loadHotelResult() {
        viewModelScope.launch {
            when (val hotelResult = getHotelDataUseCase.invoke()) {
                is Result.Success -> {
                    _state.value = HotelScreenState.Display(
                        hotel = hotelResult.body
                    )
                }
                is Result.Error -> {
                    _state.value = HotelScreenState.Error
                }
            }
        }
    }

    fun onEvent(hotelScreenEvent: HotelScreenEvent) {
        when (hotelScreenEvent) {
            is HotelScreenEvent.Reload -> {
                _state.value = HotelScreenState.Loading
                loadHotelResult()
            }
            is HotelScreenEvent.Enter -> {
                if (!_initialized) {
                    loadHotelResult()
                    _initialized = true
                }
            }
        }
    }
}