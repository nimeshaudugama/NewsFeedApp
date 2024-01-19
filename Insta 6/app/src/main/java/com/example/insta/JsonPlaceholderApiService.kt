package com.example.insta

import retrofit2.Call // Import the retrofit2 Call class
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface JsonPlaceholderApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): JsonPlaceholderApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JsonPlaceholderApiService::class.java)
        }
    }
}
