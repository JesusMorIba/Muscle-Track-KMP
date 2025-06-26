package com.jmoriba.muscletrack.utils

object ValidatorUtils {

    // Regex simple para validar formato de email
    private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    // Regex opcional más estricta (puedes usarla si quieres mayor validación)
    // private val emailRegex = Regex("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", RegexOption.IGNORE_CASE)

    /**
     * Verifica si el email tiene un formato válido usando Regex.
     */
    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && emailRegex.matches(email)
    }

    /**
     * Valida la contraseña:
     * - Mínimo 6 caracteres
     * - Contiene al menos una letra y un número (opcional pero recomendado)
     */
    fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d).{6,}$")
        return passwordRegex.matches(password)
    }

    /**
     * Verifica si las contraseñas coinciden.
     */
    fun doPasswordsMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}
