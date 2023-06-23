package org.example.model

import kotlinx.serialization.Serializable

@Serializable
data class SingUpRequest(
    val username: String,
    val password: String
)

@Serializable
data class SingUpResponse(
    val message: String
)