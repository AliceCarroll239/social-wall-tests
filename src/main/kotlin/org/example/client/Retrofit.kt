package org.example.client

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import org.example.api.SocialWallApi
import org.example.debugMessage
import org.example.model.InternalError
import org.example.model.ValidationError
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

object NetworkClient {

    private val contentType = "application/json".toMediaType()

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.NONE
    }
    private val okHtppClient = OkHttpClient().newBuilder().apply {
        addInterceptor(logging)
    }

    val client = Retrofit.Builder()
        .baseUrl("https://social-wall-learnqa.herokuapp.com")
        .addConverterFactory(Json.asConverterFactory(contentType))
        .client(okHtppClient.build())
        .build()
        .create(SocialWallApi::class.java)
}

fun RequestBody.requestBodyToString(): String {
    return try {
        val buffer = Buffer()
        this.writeTo(buffer)
        buffer.close()
        buffer.readUtf8()
    } catch (e: IOException) {
        ""
    }
}

class ErrorMessage(
    val message: String
)

fun <T> Call<T>.executeRequest(
    expectedStatus: Int,
): Pair<T?, ErrorMessage?> {
    debugMessage(
        "Request URL: ${this.request().url} \n" +
                "Method: ${this.request().method} \n" +
                "Request Body: ${this.request().body?.requestBodyToString()}"
    )
    val response = this.execute()
    val code = response.code()
    assert(code == expectedStatus)

   when (code) {
        200 -> {
            val result = response.body()
            debugMessage(
                "Response:\n " +
                        "Code: ${response.code()} \n" +
                        "Body: $result"
            )
            return Pair(result, null)
        }
        400 -> {
            val error = Json.decodeFromString<ValidationError>(response.errorBody()!!.string())
            debugMessage(
                "Response:\n " +
                        "Code: ${response.code()} \n" +
                        "Error: $error"
            )
            return Pair(null, ErrorMessage(error.message))
        }
        500 -> {
            val error = Json.decodeFromString<InternalError>(response.errorBody()!!.string())
            debugMessage(
                "Response:\n " +
                        "Code: ${response.code()} \n" +
                        "Error: $error"
            )
            return Pair(null, ErrorMessage(error.message))
        }
        else -> throw RuntimeException("Unknown response code")
    }
}