package com.james.restcountries.data

// This class means one country object is returned by API
data class Country(
    val name: Name,
    val capital: List<String>?,
    val region: String?,
    val population: Long?,
    val flags: Flags?
)
// nested object with name info
data class Name(
    val common: String
)

// Object contains flag URLS
data class Flags(
    val png: String?
)