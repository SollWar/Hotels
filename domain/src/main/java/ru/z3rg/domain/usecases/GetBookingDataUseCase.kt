package ru.z3rg.domain.usecases

import ru.z3rg.domain.models.Booking
import ru.z3rg.domain.models.Result
import ru.z3rg.domain.repository.HotelApiRepository
import javax.inject.Inject

class GetBookingDataUseCase @Inject constructor(
    private val hotelApiRepository: HotelApiRepository
) {
    suspend operator fun invoke(): Result<Booking> {
        return hotelApiRepository.getBookingInfo()
    }
}