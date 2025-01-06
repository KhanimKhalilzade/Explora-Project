package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns

class CredentialsManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserCredentials", Context.MODE_PRIVATE)
    private val credentialsMap: MutableMap<String, String> = mutableMapOf()

    init {
        sharedPreferences.all.forEach { (key, value) ->
            if (value is String) {
                credentialsMap[key] = value
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 8 &&
                password.any { it.isDigit() } &&
                password.any { it.isLetter() } &&
                password.any { !it.isLetterOrDigit() }
    }

    fun register(email: String, password: String): String {
        val normalizedEmail = email.trim().lowercase()

        if (credentialsMap.containsKey(normalizedEmail)) {
            return "Email already exists."
        }

        if (!isValidEmail(normalizedEmail)) {
            return "Please enter a valid email address."
        }

        if (!isValidPassword(password)) {
            return "Password must be at least 8 characters, include letters, numbers, and special characters."
        }

        credentialsMap[normalizedEmail] = password
        sharedPreferences.edit().putString(normalizedEmail, password).apply()

        return "Registration successful."
    }
}