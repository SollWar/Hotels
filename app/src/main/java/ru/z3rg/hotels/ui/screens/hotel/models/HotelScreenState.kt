package ru.z3rg.hotels.ui.screens.hotel.models

import ru.z3rg.domain.models.Hotel

sealed class HotelScreenState {
    data class Display(
        val hotel: Hotel
    ): HotelScreenState()
}