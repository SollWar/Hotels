package com.example.data.repository

import com.example.data.mapper.bookingResponseToBooking
import com.example.data.mapper.hotelResponseToHotel
import com.example.data.mapper.roomResponseToRoomList
import com.example.data.retrofit.HotelsApi
import ru.z3rg.domain.models.Booking
import ru.z3rg.domain.models.Hotel
import ru.z3rg.domain.models.Result
import ru.z3rg.domain.models.Room
import ru.z3rg.domain.repository.HotelApiRepository
import java.lang.Exception
import javax.inject.Inject

class HotelApiRepositoryImpl @Inject constructor(
    private val hotelsApi: HotelsApi
): HotelApiRepository {

    override suspend fun getHotelInfo(): Result<Hotel> {
        return try {
            val request = hotelsApi.getHotelInfo()
            Result.Success(
                body = hotelResponseToHotel(request.body()!!)
            )
        } catch (e: Exception) {
            Result.Error(
                errorMessage = e.message.toString()
            )
        }
    }

    override suspend fun getRoomList(): Result<List<Room>> {
        return try {
            val request = hotelsApi.getRoomList()
            Result.Success(
                body = roomResponseToRoomList(request.body()!!)
            )
        } catch (e: Exception) {
            Result.Error(
                errorMessage = e.message.toString()
            )
        }
    }

    override suspend fun getBookingInfo(): Result<Booking> {
        return try {
            val request = hotelsApi.getBookingInfo()
            Result.Success(
                body = bookingResponseToBooking(request.body()!!)
            )
        } catch (e: Exception) {
            Result.Error(
                errorMessage = e.message.toString()
            )
        }
    }


}