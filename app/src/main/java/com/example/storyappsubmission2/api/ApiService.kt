package com.example.storyappsubmission2.api

import com.example.storyappsubmission2.response.AddResponse
import com.example.storyappsubmission2.response.LoginResponse
import com.example.storyappsubmission2.response.RegisterRequest
import com.example.storyappsubmission2.response.RegisterResponse
import com.example.storyappsubmission2.response.StoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("register")
    fun register(
        @Body regCredential : RegisterRequest
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @Multipart
    @POST("stories")
    fun addStory(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): Call<AddResponse>

    @GET("stories")
    suspend fun getStories(
        @Query("page") page: Int=1,
        @Query("size") size: Int=20,
    ): StoriesResponse

    @GET("stories")
    suspend fun getStoriesWithLoc(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 50,
        @Query("location") location: Int = 1
    ): StoriesResponse
}