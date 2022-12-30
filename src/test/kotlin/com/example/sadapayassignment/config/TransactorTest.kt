package com.example.sadapayassignment.config

import com.example.sadapayassignment.createTransactionMock
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TransactorTest {

    @Test
    fun `inTransaction should execute lambda expression within transaction`() {
        val transactor = createTransactionMock<Int>()
        val result = transactor.inTransaction {
            1 + 1
        }
        assertEquals(2, result)
    }

    @Test
    fun `inTransaction should handle exceptions thrown within transaction block`() {
        val transactor = createTransactionMock<Int>()
        assertThrows(IllegalArgumentException::class.java) {
            transactor.inTransaction {
                throw IllegalArgumentException()
            }
        }
    }
}