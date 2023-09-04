package ru.z3rg.hotels.ui.screens.listroom.models

import ru.z3rg.domain.models.Room

sealed class ListRoomScreenState {
    data class Display(
        val listRoom: List<Room>
    ): ListRoomScreenState()
}