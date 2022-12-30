package com.example.sadapayassignment.config

import com.example.sadapayassignment.createTransactionMock
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TransactorTest {
    private val transactor = createTransactionMock<Int>()
    @Test
    fun `inTransaction should execute lambda expression within transaction`() {
        val result = transactor.inTransaction {
            1 + 1
        }
        assertEquals(2, result)
    }

    @Test
    fun `inTransaction should handle exceptions thrown within transaction block`() {
        assertThrows<IllegalArgumentException> {
            transactor.inTransaction<Int> {
                throw IllegalArgumentException()
            }
        }
    }
}