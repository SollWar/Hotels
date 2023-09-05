package com.example.data.retrofit

import com.example.data.models.BookingResponse
import com.example.data.models.HotelsResponse
import com.example.data.models.RoomResponse
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET

interface HotelsApi {

    @Provides
    @GET("/v3/35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotelInfo() : Response<HotelsResponse>

    @Provides
    @GET("/v3/f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getRoomList() : Response<RoomResponse>

    @Provides
    @GET("/v3/e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getBookingInfo() : Response<BookingResponse>
}