package com.example.data.mapper

import com.example.data.models.BookingResponse
import com.example.data.models.HotelsResponse
import com.example.data.models.RoomData
import com.example.data.models.RoomResponse
import ru.z3rg.domain.models.AboutTheHotel
import ru.z3rg.domain.models.Booking
import ru.z3rg.domain.models.Hotel
import ru.z3rg.domain.models.Room

fun hotelResponseToHotel(hotelsResponse: HotelsResponse): Hotel {
    return Hotel(
        id = hotelsResponse.id,
        name = hotelsResponse.name,
        address = hotelsResponse.adress,
        minimalPrice = hotelsResponse.minimal_price,
        priceForIt = hotelsResponse.price_for_it,
        rating = hotelsResponse.rating,
        ratingName = hotelsResponse.rating_name,
        imageUrls = hotelsResponse.image_urls,
        aboutTheHotel = AboutTheHotel(
            description = hotelsResponse.about_the_hotel.description,
            peculiarities = hotelsResponse.about_the_hotel.peculiarities
        )
    )
}

fun bookingResponseToBooking(bookingResponse: BookingResponse): Booking {
    return Booking(
        id = bookingResponse.id,
        hotelName = bookingResponse.hotel_name,
        hotelAddress = bookingResponse.hotel_adress,
        rating = bookingResponse.horating,
        ratingName = bookingResponse.rating_name,
        departure = bookingResponse.departure,
        arrivalCountry = bookingResponse.arrival_country,
        tourDateStart = bookingResponse.tour_date_start,
        tourDateStop = bookingResponse.tour_date_stop,
        numberOfNights = bookingResponse.number_of_nights,
        room = bookingResponse.room,
        nutrition = bookingResponse.nutrition,
        tourPrice = bookingResponse.tour_price,
        fuelCharge = bookingResponse.fuel_charge,
        serviceCharge = bookingResponse.service_charge
    )
}

fun roomResponseToRoomList(roomResponse: RoomResponse): List<Room> {
    val list = mutableListOf<Room>()
    roomResponse.rooms.forEach {
        list.add(roomDataToRoom(it))
    }
    return list
}

fun roomDataToRoom(roomData: RoomData): Room {
    return Room(
        id = roomData.id,
        name = roomData.name,
        price = roomData.price,
        pricePer = roomData.price_per,
        peculiarities = roomData.peculiarities,
        imageUrls = roomData.image_urls
    )
}