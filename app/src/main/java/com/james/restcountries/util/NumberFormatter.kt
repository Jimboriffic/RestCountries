package com.james.restcountries.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object NumberFormatter {
    fun populationDotGrouping(value: Long): String {
        val symbols = DecimalFormatSymbols().apply {
            groupingSeparator = '.'
            decimalSeparator = ','
        }

        return DecimalFormat("#,##0", symbols).apply {
            isGroupingUsed = true
            groupingSize = 3
        }.format(value)
    }
}
