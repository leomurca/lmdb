package xyz.leomurca.lmdb.utils

interface DateFormatter {
    fun dateStringToPattern(dateString: String, pattern: DatePattern): String
}

enum class DatePattern(val value: String) {
    yyyy("yyyy"),
}
