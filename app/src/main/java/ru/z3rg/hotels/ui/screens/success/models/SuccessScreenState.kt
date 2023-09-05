package ru.z3rg.hotels.ui.screens.success.models

sealed class SuccessScreenState {
    data class Display(
        val number: Int
    ) : SuccessScreenState()
}