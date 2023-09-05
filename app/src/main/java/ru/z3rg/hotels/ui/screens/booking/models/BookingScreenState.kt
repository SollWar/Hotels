package ru.z3rg.hotels.ui.screens.booking.models

import androidx.compose.runtime.snapshots.SnapshotStateList
import ru.z3rg.domain.models.Booking
import ru.z3rg.domain.models.TouristData

sealed class BookingScreenState {
    data class Display(
        val booking: Booking,
        val tourist: SnapshotStateList<TouristData>,
        var phone: String = "",
        var email: String = ""
    ): BookingScreenState()
    data object Loading: BookingScreenState()
    data object Error: BookingScreenState()
}