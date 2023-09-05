package ru.z3rg.domain.models

data class TouristData(
    var firstName: String = "",
    var secondName: String = "",
    var dateOfBirth: String = "",
    var nationality: String = "",
    var pasNumber: String = "",
    var pasValidPeriod: String = ""
) {
    fun isValidData(): Boolean {
        return (firstName.isNotEmpty() && secondName.isNotEmpty() && dateOfBirth.isNotEmpty()
                && nationality.isNotEmpty() && pasNumber.isNotEmpty() && pasValidPeriod.isNotEmpty())
    }
}