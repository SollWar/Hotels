package ru.z3rg.hotels.ui.screens.booking.models

import ru.z3rg.domain.models.Booking

sealed class BookingScreenState {
    data class Display(
        val booking: Booking
    ): BookingScreenState()
}