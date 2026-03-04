package com.james.restcountries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.james.restcountries.ui.DetailScreen
import com.james.restcountries.ui.SearchScreen
import com.james.restcountries.ui.theme.RestcountriesTheme
import com.james.restcountries.viewmodel.CountryViewModel
import com.james.restcountries.data.Country


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RestcountriesTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()

                    // Create shared ViewModel
                    val countryViewModel: CountryViewModel = viewModel()

                    NavHost(navController = navController, startDestination = "search")
                    {

                        composable("search") {
                            SearchScreen(navController)
                        }

                        composable("detail/{countryName}") { backStackEntry ->
                            val countryName = backStackEntry.arguments?.getString("countryName")

                            DetailScreen(countryName)

                        }
                    }
                }
            }
        }
    }
}