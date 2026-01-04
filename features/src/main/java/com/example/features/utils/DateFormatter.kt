package com.example.features.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {

    fun formatDate(input: String): String {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val date = LocalDate.parse(input)
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
            date.format(formatter).replaceFirstChar { it.uppercase() }
        } else {
            formatDateLegacy(input)
        }
    }

    private fun formatDateLegacy(input: String): String {
        val inputFormat = java.text.SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = java.text.SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
        val date = inputFormat.parse(input) ?: return input
        return outputFormat.format(date)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale("ru")) else it.toString() }
    }
}