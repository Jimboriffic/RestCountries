package com.james.restcountries.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

// Minimal theme wrapper.
// This avoids references to Color.kt (Purple80, etc.) and Type.kt (Typography).
@Composable
fun RestcountriesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}