package com.james.restcountries.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.james.restcountries.viewmodel.CountryViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun DetailScreen(
    countryNameEncoded: String?,
    viewModel: CountryViewModel = viewModel() // ✅ default
) {
    val decodedName = countryNameEncoded?.let {
        URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
    }

    val country = viewModel.countries.value.find { it.name.common == decodedName }

    if (country == null) {
        Text("Country not found")
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Name: ${country.name.common}")
        Text("Capital: ${country.capital?.joinToString() ?: "N/A"}")
        Text("Region: ${country.region ?: "N/A"}")
        Text("Population: ${country.population ?: "N/A"}")
    }
}