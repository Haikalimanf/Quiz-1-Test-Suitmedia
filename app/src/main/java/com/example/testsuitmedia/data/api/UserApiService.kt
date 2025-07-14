package com.example.testsuitmedia.data.api

import com.example.testsuitmedia.data.response.ResponseUsername
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {
    @GET("users")
    suspend fun getUser(
        @Query("page") page: Int ,
        @Query("per_page") perPage: Int
    ): ResponseUsername
}