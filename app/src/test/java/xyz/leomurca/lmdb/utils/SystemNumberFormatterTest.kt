package xyz.leomurca.lmdb.utils

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SystemNumberFormatterTest {

    private val systemNumberFormatter = SystemNumberFormatter()

    @Test
    fun `when call usdCurrency then returns dollar formatted string`() {
        // Arrange
        val rawNumber = 220597098F

        // Act
        val result = systemNumberFormatter.usdCurrency(rawNumber)

        // Assert
        Assert.assertEquals("\$220,597,104.00", result)
    }
}