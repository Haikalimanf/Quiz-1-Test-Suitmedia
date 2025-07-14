package com.example.testsuitmedia.data.retrofit

import com.example.testsuitmedia.data.api.UserApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private const val BASE_URL = "https://reqres.in/api/"
        private const val API_KEY = "reqres-free-v1"
        private const val HEADERS_API_KEY = "x-api-key"

        fun getApiService(): UserApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val apiKeyInterceptor = Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(HEADERS_API_KEY, API_KEY)
                    .build()
                chain.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(apiKeyInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(UserApiService::class.java)
        }
    }
}
