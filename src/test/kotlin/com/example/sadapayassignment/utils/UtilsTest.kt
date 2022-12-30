package com.example.sadapayassignment.utils

import com.example.sadapayassignment.alsoAssertResponse
import com.example.sadapayassignment.exception.InvalidArgumentException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class UtilsTest {

    @Nested
    inner class TestsForCheckRequired {

        @Test
        fun `test checkRequired with empty value`() {
            assertThrows<InvalidArgumentException> {
                checkRequired("", "Field Name")
            }.alsoAssertResponse {
                assertEquals("Field Name is required", message)
            }
        }

        @Test
        fun `test checkRequired with null value`() {
            assertThrows<InvalidArgumentException> {
                checkRequired(null, "Field Name")
            }.alsoAssertResponse {
                assertEquals("Field Name is required", message)
            }
        }
    }
}