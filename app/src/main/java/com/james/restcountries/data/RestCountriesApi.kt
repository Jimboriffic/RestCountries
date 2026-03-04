package com.james.restcountries.data

import retrofit2.http.GET
import retrofit2.http.Query


// Gets all countries from API
interface RestCountriesApi {

    @GET("v3.1/all")
    suspend fun getAllCountries(
        @Query("fields") fields: String = "name,capital,region,population,flags"
    ): List<Country>
}
