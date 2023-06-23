package org.example

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.example.api.SocialWallApi
import org.example.model.SingUpRequest
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val contentType = "application/json".toMediaType()
    val client = OkHttpClient().newBuilder()
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    client.apply {
        addInterceptor(logging)
    }
    val retrofit = Retrofit.Builder()
        .baseUrl("https://social-wall-learnqa.herokuapp.com")
        .addConverterFactory(Json.asConverterFactory(contentType))
        .client(client.build())
        .build()
        .create(SocialWallApi::class.java)

    println(
        retrofit.singUp(
            SingUpRequest("Alice2", "Alice")
        ).execute().body()?.message
    )
}