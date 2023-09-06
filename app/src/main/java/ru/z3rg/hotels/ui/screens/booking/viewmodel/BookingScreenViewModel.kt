package ru.z3rg.hotels.ui.screens.booking.viewmodel

import android.util.Log
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
            is BookingScreenEvent.EmailUpdate -> {
                emailUpdate(bookingScreenEvent.email)
            }
            is BookingScreenEvent.PhoneUpdate -> {
                phoneUpdate(bookingScreenEvent.phone)
            }
            is BookingScreenEvent.TouristEvent -> {
                when (bookingScreenEvent) {
                    is BookingScreenEvent.TouristEvent.UpdateDateOfBirth -> updateDateOfBirth(
                        bookingScreenEvent.index,
                        bookingScreenEvent.dateOfBirth
                    )
                    is BookingScreenEvent.TouristEvent.UpdateFirstName -> updateFirstName(
                        bookingScreenEvent.index,
                        bookingScreenEvent.firstName
                    )
                    is BookingScreenEvent.TouristEvent.UpdateNationality -> updateNationality(
                        bookingScreenEvent.index,
                        bookingScreenEvent.nationality
                    )
                    is BookingScreenEvent.TouristEvent.UpdatePasNumber -> updatePasNumber(
                        bookingScreenEvent.index,
                        bookingScreenEvent.pasNumber
                    )
                    is BookingScreenEvent.TouristEvent.UpdatePasValidPeriod -> updatePasValidPeriod(
                        bookingScreenEvent.index,
                        bookingScreenEvent.pasValidPeriod
                    )
                    is BookingScreenEvent.TouristEvent.UpdateSecondName -> updateSecondName(
                        bookingScreenEvent.index,
                        bookingScreenEvent.secondName
                    )
                }
            }
        }
    }

    private fun phoneUpdate(phone: String) {
        _state.value = (_state.value as BookingScreenState.Display).copy(
            phone = phone
        )
        Log.d("UpdateState", _state.value.hashCode().toString())
    }
    private fun emailUpdate(email: String) {
        _state.value = (_state.value as BookingScreenState.Display).copy(
            email = email
        )
    }
    private fun updateFirstName(index: Int ,firstName: String) {
        val touristList = updateTouristList()
        touristList[index].firstName = firstName
        _state.value = (_state.value as BookingScreenState.Display).copy(
            tourist = touristList
        )
    }
    private fun updateDateOfBirth(index: Int ,dateOfBirth: String) {
        val touristList = updateTouristList()
        touristList[index].dateOfBirth = dateOfBirth
        _state.value = (_state.value as BookingScreenState.Display).copy(
            tourist = touristList
        )
    }
    private fun updateNationality(index: Int ,nationality: String) {
        val touristList = updateTouristList()
        touristList[index].nationality = nationality
        _state.value = (_state.value as BookingScreenState.Display).copy(
            tourist = touristList
        )
    }
    private fun updatePasNumber(index: Int ,pasNumber: String) {
        val touristList = updateTouristList()
        touristList[index].pasNumber = pasNumber
        _state.value = (_state.value as BookingScreenState.Display).copy(
            tourist = touristList
        )
    }
    private fun updatePasValidPeriod(index: Int ,pasValidPeriod: String) {
        val touristList = updateTouristList()
        touristList[index].pasValidPeriod = pasValidPeriod
        _state.value = (_state.value as BookingScreenState.Display).copy(
            tourist = touristList
        )
    }
    private fun updateSecondName(index: Int ,secondName: String) {
        val touristList = updateTouristList()
        touristList[index].secondName = secondName
        _state.value = (_state.value as BookingScreenState.Display).copy(
            tourist = touristList
        )
    }

    private fun updateTouristList(): MutableList<TouristData> {
        val touristList = mutableStateListOf<TouristData>()
        (_state.value as BookingScreenState.Display).tourist.forEach {
            touristList.add(it)
        }
        return touristList
    }
}