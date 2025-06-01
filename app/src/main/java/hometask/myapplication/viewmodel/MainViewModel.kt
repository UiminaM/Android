package hometask.myapplication.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun validateCityName(city: String): ValidationResult {
        return when {
            city.any { it.isDigit() } -> ValidationResult.Error("Название города не должно содержать цифры")
            city.length !in 2..60 -> ValidationResult.Error("Название города должно быть от 2 до 60 символов")
            else -> ValidationResult.Success
        }
    }
}

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}
