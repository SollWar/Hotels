package ru.z3rg.hotels.ui.screens.hotel.models

sealed class HotelScreenEvent {
    data object Reload: HotelScreenEvent()
    data object Enter: HotelScreenEvent()
}