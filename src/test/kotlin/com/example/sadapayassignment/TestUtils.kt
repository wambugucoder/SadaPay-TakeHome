package com.example.sadapayassignment

import com.example.sadapayassignment.config.Transactor
import com.example.sadapayassignment.model.User
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.AssertionsForInterfaceTypes.*
import java.io.File
import java.net.URLDecoder

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
fun getClassesInPackage(packageName: String): List<Class<*>> {
    val classLoader = Thread.currentThread().contextClassLoader
    val packagePath = packageName.replace(".", "/")
    val resources = classLoader.getResources(packagePath)

    val classes = mutableListOf<Class<*>>()
    while (resources.hasMoreElements()) {
        val resource = resources.nextElement()
        if (resource.protocol == "file") {
            val filePath = URLDecoder.decode(resource.file, "UTF-8")
            val packageDirectory = File(filePath)
            if (packageDirectory.isDirectory) {
                packageDirectory.listFiles()?.forEach { file ->
                    if (file.isFile) {
                        val className = "$packageName.${file.nameWithoutExtension}"
                        classes.add(Class.forName(className))
                    }
                }
            }
        }
    }
    return classes
}

fun <T : Any> createTransactionMock(): Transactor {
    val transactor = mockk<Transactor>()
    every { transactor.inTransaction(any<() -> T>())!! } answers {
        val saveFunc = arg<() -> T>(0)
        saveFunc()
    }
    return transactor
}

fun <T : Exception> T.alsoAssertResponse(block: T.() -> Unit): T {
    block()
    return this
}