package com.example.sadapayassignment

import com.example.sadapayassignment.model.User
import org.assertj.core.api.AssertionsForInterfaceTypes.*

fun assertNoExceptionsEncountered(block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        // If an exception was caught, fail the test.
        assert(false) { "Unexpected exception: ${e.message}" }
    }
}

fun <T> assertIgnoringId(expected: T, actual: T) {
    val fieldsToIgnore = mutableListOf("id")
    if (expected is User && expected.tweets.isEmpty()) {
        fieldsToIgnore.add("tweets")
    }
    assertThat(actual).usingRecursiveComparison()
        .ignoringFields(*fieldsToIgnore.toTypedArray())
        .isEqualTo(expected)
}