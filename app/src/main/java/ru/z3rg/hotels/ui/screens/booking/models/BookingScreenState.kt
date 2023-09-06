package ru.z3rg.hotels.ui.screens.booking.models

import ru.z3rg.domain.models.Booking
import ru.z3rg.domain.models.TouristData

sealed class BookingScreenState {
    data class Display(
        val booking: Booking,
        val tourist: MutableList<TouristData>,
        var phone: String = "",
        var email: String = ""
    ): BookingScreenState()
    data object Loading: BookingScreenState()
    data object Error: BookingScreenState()
}