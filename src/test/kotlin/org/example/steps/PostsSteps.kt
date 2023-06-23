package org.example.steps

import io.qameta.allure.Step
import org.example.client.NetworkClient
import org.example.client.executeRequest
import org.example.model.CreatePostRequest
import org.example.model.GetPostResponse
import org.example.model.ReactionRequest
import org.example.models.PostModel
import org.example.models.User
import java.util.UUID
import kotlin.test.assertEquals

class PostsSteps {

    @Step("Получить все посты")
    fun getAllPosts(expectedPosts: List<GetPostResponse.Post>) {
        val response = NetworkClient
            .client.postsAllGet().executeRequest(200)
        assertEquals(expectedPosts, response.first!!.posts)
    }

    @Step("Создать пост")
    fun createPost(post: PostModel, user: User) {
        val response = NetworkClient
            .client.postsCreatePost(
                user.token, CreatePostRequest(
                    post.text
                )
            ).executeRequest(200)
        assertEquals("Post 0 creation successful", response.first!!.message)
        post.id = 0
    }

    @Step("Создаем пост неавторизованным пользователем")
    fun createPostUnauth(post: PostModel, user: User) {
        user.token = UUID.randomUUID().toString()

        val response = NetworkClient
            .client.postsCreatePost(
                user.token, CreatePostRequest(
                    post.text
                )
            ).executeRequest(400)
        assertEquals("Unknown session", response.second!!.message)
        post.id = 0
    }

    @Step("Поставить лайк")
    fun likePost(
        post: PostModel,
        user: User
    ) {
        NetworkClient.client.postsReactionPost(
            user.token,
            ReactionRequest(
                post.id, true
            )
        ).executeRequest(200)
    }

    @Step("Очистка базы постов")
    fun clean() {
        NetworkClient.client.clean().executeRequest(200)
    }
}