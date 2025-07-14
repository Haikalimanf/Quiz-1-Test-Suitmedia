package com.example.testsuitmedia.ui.third

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsuitmedia.data.repository.UserRepository
import com.example.testsuitmedia.data.response.DataItem
import kotlinx.coroutines.launch

class ThirdViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _users = MutableLiveData<List<DataItem>>()
    val users: LiveData<List<DataItem>> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val currentList = mutableListOf<DataItem>()
    private var currentPage = 1
    private val perPage = 10
    private var isLoadingInternal = false
    private var isLastPage = false

    fun loadPage() {
        if (isLoadingInternal || isLastPage) return
        isLoadingInternal = true
        _isLoading.postValue(true)

        viewModelScope.launch {
            try {
                val response = repository.getUsers(currentPage, perPage)
                if (response.isNotEmpty()) {
                    currentList.addAll(response)
                    _users.postValue(currentList)
                } else {
                    isLastPage = true
                }
            } catch (e: Exception) {
                Log.e("ThirdViewModel", "Error: ${e.message}")
            } finally {
                isLoadingInternal = false
                _isLoading.postValue(false)
            }
        }
    }
}

