package com.example.testsuitmedia.data.repository

import com.example.testsuitmedia.data.response.DataItem
import com.example.testsuitmedia.data.retrofit.ApiConfig

class UserRepository {
    private val apiService = ApiConfig.getApiService()

    suspend fun getUsers(page: Int, perPage: Int): List<DataItem> {
        val response = apiService.getUser(page, perPage)
        return response.data
    }
}