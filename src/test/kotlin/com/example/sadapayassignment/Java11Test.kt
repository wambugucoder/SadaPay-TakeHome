package com.example.sadapayassignment

import junit.framework.TestCase.*
import org.junit.jupiter.api.Test

class Java11Test {

    @Test
    fun testJavaVersion() {
        val version = System.getProperty("java.version")
        assertTrue(version.startsWith("11"))
    }
    
}