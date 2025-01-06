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
        return password.isNotEmpty()
    }

    fun register(email: String, password: String): String {
        val normalizedEmail = email.trim().lowercase()

        if (credentialsMap.containsKey(normalizedEmail)) {
            return "This email is already registered."
        }

        if (!isValidEmail(normalizedEmail)) {
            return "Enter a valid email address."
        }

        if (!isValidPassword(password)) {
            return "Password field cannot be blank."
        }

        credentialsMap[normalizedEmail] = password
        sharedPreferences.edit().putString(normalizedEmail, password).apply()

        return "You have successfully registered."
    }
}