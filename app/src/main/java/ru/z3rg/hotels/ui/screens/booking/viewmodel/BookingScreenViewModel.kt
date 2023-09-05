package ru.z3rg.hotels.ui.screens.booking.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.z3rg.domain.models.Result
import ru.z3rg.domain.models.TouristData
import ru.z3rg.domain.usecases.GetBookingDataUseCase
import ru.z3rg.hotels.ui.screens.booking.models.BookingScreenEvent
import ru.z3rg.hotels.ui.screens.booking.models.BookingScreenState
import javax.inject.Inject

@HiltViewModel
class BookingScreenViewModel @Inject constructor(
    private val getBookingDataUseCase: GetBookingDataUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<BookingScreenState> = MutableStateFlow(BookingScreenState.Loading)
    val state = _state.asStateFlow()

    private var _initialized = false

    private fun loadBookingResult() {
        viewModelScope.launch {
            when (val bookingResult = getBookingDataUseCase.invoke()) {
                is Result.Success -> {
                    _state.value = BookingScreenState.Display(
                        booking = bookingResult.body,
                        tourist = mutableStateListOf(TouristData())
                    )
                }
                is Result.Error -> {
                    _state.value = BookingScreenState.Error
                }
            }
        }
    }

    fun onEvent(bookingScreenEvent: BookingScreenEvent) {
        when (bookingScreenEvent) {
            is BookingScreenEvent.Reload -> {
                _state.value = BookingScreenState.Loading
                loadBookingResult()
            }
            is BookingScreenEvent.Enter -> {
                if (!_initialized) {
                    loadBookingResult()
                    _initialized = true
                }
            }
        }
    }
}