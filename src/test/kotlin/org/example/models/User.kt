package org.example.models

import org.example.model.GetPostResponse
import kotlin.properties.Delegates

class User(
    val username: String,
    val password: String
) {
    lateinit var token: String

    override fun toString(): String = "Username $username, Password $password"
}

class PostModel(
    val text: String
) {
    var id by Delegates.notNull<Int>()

    lateinit var reactions: GetPostResponse.Post.Reactions
}