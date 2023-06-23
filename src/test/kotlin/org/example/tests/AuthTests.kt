package org.example.tests

import io.qameta.allure.Description
import org.example.BaseTest
import org.example.generateRandomString
import org.example.models.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AuthTests: BaseTest() {

    private lateinit var user: User

    @BeforeEach
    fun initUser() {
        user = User(
            username = generateRandomString(6),
            password = generateRandomString(6)
        )
    }

    @Test
    @Description("Регистрация пользователя")
    fun singUpNewUser() {
        authSteps.singUp(user)
    }

    @Test
    fun singInNewUser() {
        authSteps.singUp(user)
        authSteps.singIn(user)
    }
}