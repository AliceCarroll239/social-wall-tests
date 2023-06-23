package org.example.model

import kotlinx.serialization.Serializable

@Serializable
data class GetPostResponse(
    val posts: List<Post> = listOf()
) {
    @Serializable
    data class Post(
        val author: String,
        val id: Int,
        val reactions: Reactions = Reactions(),
        val text: String
    ) {
        @Serializable
        data class Reactions(
            val dislikes: List<Dislikes> = listOf(),
            val likes: List<Like> = listOf()
        ) {
            @Serializable
            data class Like(
                val username: String
            )
            @Serializable
            data class Dislikes(
                val username: String
            )
        }
    }
}

@Serializable
data class CreatePostRequest(
    val text: String
)

@Serializable
data class CreatePostResponse(
    val message: String
)

@Serializable
data class DeletePostRequest(
    val id: Int
)

@Serializable
data class ReactionRequest(
    val id: Int,
    val reaction: Boolean
)

@Serializable
data class UpdatePostRequest(
    val id: Int,
    val text: String
)

@Serializable
data class DeletePostResponse(
    val message: String
)

@Serializable
data class ReactionResponse(
    val message: String
)

@Serializable
data class UpdatePostResponse(
    val message: String
)

@Serializable
data class ValidationError(
    val message: String
)

@Serializable
data class InternalError(
    val message: String
)