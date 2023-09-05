package ru.z3rg.domain.repository

import ru.z3rg.domain.models.Booking
import ru.z3rg.domain.models.Hotel
import ru.z3rg.domain.models.Room
import ru.z3rg.domain.models.Result

interface HotelApiRepository {
    suspend fun getHotelInfo(): Result<Hotel>
    suspend fun getRoomList(): Result<List<Room>>
    suspend fun getBookingInfo(): Result<Booking>
}