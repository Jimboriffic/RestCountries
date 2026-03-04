package com.james.restcountries.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.james.restcountries.viewmodel.CountryViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

// get CountryViewModel for this screen
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: CountryViewModel = viewModel()
) { // read current search text from ViewModel
    val query by viewModel.searchQuery
    val countries = viewModel.filteredCountries()

    // layout container
    Column {
        Spacer(modifier = Modifier.height(32.dp))

        Text("Loaded: ${viewModel.countries.value.size}")
        TextField(
            value = query,
            onValueChange = { viewModel.updateSearch(it) }, // Updates ViewModel when user inputs text
            label = { Text("Search country") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        // Scrollable list of countries
        LazyColumn {
            items(countries) { country ->
                val name = country.name.common

                Text(
                    text = name, // only shows the country name
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // For countries with spaces like United Kingdom
                            val encoded = URLEncoder.encode(name, StandardCharsets.UTF_8.toString())
                            // Navigate to detail screen
                            navController.navigate("detail/$encoded")
                        } // adds spaces around the item
                        .padding(16.dp)
                )
            }
        }
    }
}