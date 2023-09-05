package ru.z3rg.hotels.ui.screens.booking.models

sealed class BookingScreenEvent {
    data object Reload: BookingScreenEvent()
    data object Enter: BookingScreenEvent()
}