package com.example.goodweather.data.date

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale

object CurrentDate {
     val currentDate: LocalDateTime = LocalDateTime.now()
     val form: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
}