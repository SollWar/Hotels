package ru.z3rg.domain.usecases

import ru.z3rg.domain.models.Result
import ru.z3rg.domain.models.Room
import ru.z3rg.domain.repository.HotelApiRepository
import javax.inject.Inject

class GetRoomListUseCase @Inject constructor(
    private val hotelApiRepository: HotelApiRepository
) {
    suspend operator fun invoke(): Result<List<Room>> {
        return hotelApiRepository.getRoomList()
    }
}