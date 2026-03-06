package com.james.restcountries.ui

// // I used ".*" as much as possible for a clean look, but maybe it's too cpu intensive?
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.james.restcountries.viewmodel.CountryViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.Collator
import java.util.Locale


// get CountryViewModel for this screen
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: CountryViewModel
) {
    // read current search text from ViewModel
    val query by viewModel.searchQuery
    // The variables below make sure the country list is sorted alphabetically
    val collator = remember { Collator.getInstance(Locale.getDefault()) }
    val countries = viewModel.filteredCountries()
        .sortedWith(compareBy(collator) { it.name.common })


    var listExpanded by remember { mutableStateOf(false) }

    // layout container
    Column {
        // debug status
        if (viewModel.isLoading.value) Text("Loading...")
        viewModel.error.value?.let { Text("Error: $it") }


        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = query,
            onValueChange = {
                viewModel.updateSearch(it) // Updates ViewModel when user inputs text
            listExpanded = true
           },
            label = { Text("Search country") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            trailingIcon = {
                Text(
                    text = if (listExpanded) "Hide countries" else "Show countries",
                    modifier = Modifier
                        .clickable { listExpanded = !listExpanded }
                        .padding(end = 12.dp)
                )
                }
        )

        // Shows the list of countries with an animation (expand/collapse)
    AnimatedVisibility(visible = listExpanded) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(
                items = countries,
            key = { it.name.common }
            ) { country ->
                val name = country.name.common
                Text(
                    text = name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val encoded = URLEncoder.encode(name, StandardCharsets.UTF_8.toString())
                            navController.navigate("detail/$encoded")
                            listExpanded = false
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}
}
