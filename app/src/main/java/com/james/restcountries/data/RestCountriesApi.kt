package com.james.restcountries.data

import retrofit2.http.GET

// Gets all countries from API
interface RestCountriesApi {

    @GET("v3.1/all")
    suspend fun getAllCountries(): List<Country>
}