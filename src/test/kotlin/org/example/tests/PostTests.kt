package org.example.tests

import org.example.BaseTest
import org.example.generateRandomString
import org.example.model.GetPostResponse
import org.example.models.PostModel
import org.example.models.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PostTests: BaseTest() {
    private lateinit var user: User
    private lateinit var secondUser: User
    private lateinit var post: PostModel

    @BeforeEach
    fun initUser() {
        user = User(
            username = generateRandomString(6),
            password = generateRandomString(6)
        )
        secondUser = User(
            username = generateRandomString(6),
            password = generateRandomString(6)
        )
        post = PostModel(
            "Hello World!"
        )
        postSteps.clean()

        authSteps.singUp(user)
        authSteps.singIn(user)

        authSteps.singUp(secondUser)
        authSteps.singIn(secondUser)
    }

    @Test
    fun createPost() {
        postSteps.createPost(post, user)

        val expectedPostsList = listOf(
            GetPostResponse.Post(
                author = user.username,
                id = post.id,
                reactions = GetPostResponse.Post.Reactions(
                    listOf(), listOf()
                ),
                text = post.text
            )
        )

        postSteps.getAllPosts(expectedPostsList)
    }

    @Test
    fun createPostUnauth() {
        postSteps.createPostUnauth(post, user)

        val expectedPostList = listOf<GetPostResponse.Post>()
        postSteps.getAllPosts(expectedPostList)
    }

    @Test
    fun likePostTest() {
        postSteps.createPost(post, user)
        postSteps.likePost(post, secondUser)

        val expectedPostsList = listOf(
            GetPostResponse.Post(
                author = user.username,
                id = post.id,
                reactions = GetPostResponse.Post.Reactions(
                    likes = listOf(
                        GetPostResponse.Post.Reactions.Like(
                            secondUser.username
                        )
                    ),
                    dislikes = listOf()
                ),
                text = post.text
            )
        )
        postSteps.getAllPosts(expectedPostsList)
    }
}