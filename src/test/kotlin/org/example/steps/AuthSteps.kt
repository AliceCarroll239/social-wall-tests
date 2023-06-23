package org.example.steps

import io.qameta.allure.Step
import org.example.client.NetworkClient
import org.example.client.executeRequest
import org.example.model.SingInRequest
import org.example.model.SingUpRequest
import org.example.models.User

class AuthSteps {

    @Step("Регистрация \"{user.username}\"")
    fun singUp(user: User) {
        val response = NetworkClient.client.singUp(
            SingUpRequest(
                user.username, user.password
            )
        ).executeRequest(200)
        assert(response.first!!.message == "Hello ${user.username}!!")
    }

    @Step("Авторизация \"{user.username}\"")
    fun singIn(user: User) {
        val response = NetworkClient.client.singIn(
            SingInRequest(
                user.username, user.password
            )
        ).executeRequest(200)
        assert(response.first!!.username == user.username)
        user.token = response.first!!.token
    }
}