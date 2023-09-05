package ru.z3rg.hotels.ui.screens.success.models

sealed class SuccessScreenEvent {
    data object Enter: SuccessScreenEvent()
}