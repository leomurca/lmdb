package xyz.leomurca.lmdb.utils

import android.icu.text.NumberFormat
import android.icu.util.Currency

class SystemNumberFormatter : NumberFormatter {

    override fun usdCurrency(number: Float): String {
        val formatter = NumberFormat.getCurrencyInstance().apply {
            currency = Currency.getInstance("USD")
        }
        return formatter.format(number)
    }
}