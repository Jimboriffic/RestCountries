package com.james.restcountries.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.james.restcountries.data.ApiClient
import com.james.restcountries.data.Country
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    // Contains list of countries fetched by api
    private val _countries = mutableStateOf<List<Country>>(emptyList())
    val countries: State<List<Country>> = _countries

    // Contains search text entered by the user
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error



    // Runs automatically when ViewModel is created
    init {
        fetchCountries()
    }

    // Calls api using coroutine
    private fun fetchCountries() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _countries.value = ApiClient.api.getAllCountries()
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }


    // Updates the search query when user types
    fun updateSearch(query: String) {
        _searchQuery.value = query
    }

    // Filters countries based on search input
    fun filteredCountries(): List<Country> {
        return _countries.value.filter {
            it.name.common.contains(
                _searchQuery.value,
                ignoreCase = true)
        }
    }
}