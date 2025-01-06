package com.example.myapplication
class CredentialsManagerTest {

    private lateinit var credentialsManager: CredentialsManager


    fun testEmptyEmail() {
        val email = ""
        if (!credentialsManager.isValidEmail(email)) {
            println("testEmptyEmail passed: Email is invalid as expected")
        } else {
            println("testEmptyEmail failed: Email should be invalid if it's empty")
        }
    }
    fun testWrongFormatEmail() {
        val email = "invalid-email"
        if (!credentialsManager.isValidEmail(email)) {
            println("testWrongFormatEmail passed: Email is invalid as expected")
        } else {
            println("testWrongFormatEmail failed: Email should be invalid if it doesn't have the correct format")
        }
    }
    fun testValidEmail() {
        val email = "test@example.com"
        if (credentialsManager.isValidEmail(email)) {
            println("testValidEmail passed: Email is valid as expected")
        } else {
            println("testValidEmail failed: Email should be valid if it has the correct format")
        }
    }

    fun testEmptyPassword() {
        val password = ""
        if (!credentialsManager.isValidPassword(password)) {
            println("testEmptyPassword passed: Password is invalid as expected")
        } else {
            println("testEmptyPassword failed: Password should be invalid if it's empty")
        }
    }

    fun testValidPassword() {
        val password = "password123"
        if (credentialsManager.isValidPassword(password)) {
            println("testValidPassword passed: Password is valid as expected")
        } else {
            println("testValidPassword failed: Password should be valid if it's not empty")
        }
    }

    fun runTests() {

        testEmptyEmail()
        testWrongFormatEmail()
        testValidEmail()
        testEmptyPassword()
        testValidPassword()
    }


}