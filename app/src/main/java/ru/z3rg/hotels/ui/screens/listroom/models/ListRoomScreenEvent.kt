package ru.z3rg.hotels.ui.screens.listroom.models

sealed class ListRoomScreenEvent {
    data object Reload: ListRoomScreenEvent()
    data object Enter: ListRoomScreenEvent()
}