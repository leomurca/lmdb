package xyz.leomurca.lmdb.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SystemDateFormatter : DateFormatter {
    override fun dateStringToPattern(dateString: String, pattern: DatePattern): String {
        if (dateString.isBlank()) return ""
        val localDate = LocalDate.parse(dateString)
        val formatter = DateTimeFormatter.ofPattern(pattern.value)
        return localDate.format(formatter)
    }
}

