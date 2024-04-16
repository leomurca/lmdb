package xyz.leomurca.lmdb.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class SystemDateFormatterTest {


    private val systemDateFormatter = SystemDateFormatter()

    @Test
    fun `when DatePattern is yyyy then returns the year`() {
        // Arrange
        val dateString = "2023-01-22"
        val datePattern = DatePattern.yyyy

        // Act
        val result = systemDateFormatter.dateStringToPattern(dateString, datePattern)

        // Assert
        assertEquals("2023", result)

    }
}