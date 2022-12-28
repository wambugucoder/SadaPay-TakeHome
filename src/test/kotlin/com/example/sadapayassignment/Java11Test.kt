package com.example.sadapayassignment

import junit.framework.TestCase.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.stream.Collectors
import java.util.stream.IntStream

class Java11Test {

    @Test
    fun testJavaVersion() {
        val version = System.getProperty("java.version")
        assertTrue(version.startsWith("11"))
    }

    @Test
    fun java11LanguageTest() {
        Assertions.assertTrue("".isBlank())
        Assertions.assertEquals(
            listOf(2, 6),
            IntStream.of(1, 2, 3, 5, 6, 7)
                .filter { i: Int -> i % 2 == 0 }
                .boxed()
                .collect(Collectors.toList())
        )
    }
}