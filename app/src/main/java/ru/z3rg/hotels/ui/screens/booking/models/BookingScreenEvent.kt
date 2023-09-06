package ru.z3rg.hotels.ui.screens.booking.models

sealed class BookingScreenEvent {
    data object Reload: BookingScreenEvent()
    data object Enter: BookingScreenEvent()
    data class PhoneUpdate(
        val phone: String
    ): BookingScreenEvent()
    data class EmailUpdate(
        val email: String
    ): BookingScreenEvent()
    sealed class TouristEvent: BookingScreenEvent() {
        data class UpdateFirstName(
            val index: Int,
            val firstName: String
        ): TouristEvent()
        data class UpdateSecondName(
            val index: Int,
            val secondName: String
        ): TouristEvent()
        data class UpdateDateOfBirth(
            val index: Int,
            val dateOfBirth: String
        ): TouristEvent()
        data class UpdateNationality(
            val index: Int,
            val nationality: String
        ): TouristEvent()
        data class UpdatePasNumber(
            val index: Int,
            val pasNumber: String
        ): TouristEvent()
        data class UpdatePasValidPeriod(
            val index: Int,
            val pasValidPeriod: String
        ): TouristEvent()
    }
}