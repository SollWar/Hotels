package ru.z3rg.domain.usecases

import ru.z3rg.domain.models.Hotel
import ru.z3rg.domain.models.Result
import ru.z3rg.domain.repository.HotelApiRepository
import javax.inject.Inject

class GetHotelDataUseCase @Inject constructor(
    private val hotelApiRepository: HotelApiRepository
) {
    suspend operator fun invoke(): Result<Hotel> {
        return hotelApiRepository.getHotelInfo()
    }
}