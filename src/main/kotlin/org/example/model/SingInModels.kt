package org.example.model

import kotlinx.serialization.Serializable

@Serializable
data class SingInRequest(
    val username: String,
    val password: String
)

@Serializable
data class SingInResponse(
    val username: String,
    val token: String
)