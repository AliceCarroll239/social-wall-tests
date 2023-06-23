package org.example.api

import org.example.model.*
import retrofit2.Call
import retrofit2.http.*

interface SocialWallApi {

    @POST("/auth/singup")
    fun singUp(
        @Body singUpRequest: SingUpRequest
    ): Call<SingUpResponse>

    @POST("/auth/singin")
    fun singIn(
        @Body singUpRequest: SingInRequest
    ): Call<SingInResponse>

    @GET("posts/all")
    fun postsAllGet(): Call<GetPostResponse>

    @POST("posts/create")
    fun postsCreatePost(
        @Header("token") token: String,
        @Body createPostRequest: CreatePostRequest
    ): Call<CreatePostResponse>

    @DELETE("posts/delete")
    fun postsDeleteDelete(
        @Header("token") token: String,
        @Body deletePostRequest: DeletePostRequest
    ): Call<DeletePostResponse>

    @POST("posts/reaction")
    fun postsReactionPost(
        @Header("token") token: String,
        @Body reactionRequest: ReactionRequest
    ): Call<ReactionResponse>


    @PATCH("posts/update")
    fun postsUpdatePatch(
        @Header("token") token: String,
        @Body updatePostRequest: UpdatePostRequest
    ): Call<UpdatePostResponse>

    @GET("admin/clean")
    fun clean(): Call<GetPostResponse>
}