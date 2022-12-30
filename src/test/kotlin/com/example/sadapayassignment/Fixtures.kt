package com.example.sadapayassignment

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

object Fixtures {

    const val POSTGRES_IMAGE = "postgres:11.5"
    const val POSTGRES_DBNAME = "testDb"
    const val POSTGRES_USERNAME = "test"
    const val POSTGRES_PASSWORD = "test"
    const val REPO_PACKAGE = "com.example.sadapayassignment.repository"
    const val MODEL_PACKAGE = "com.example.sadapayassignment.model"
}

data class TestClass(

    @field:NotNull(message = "field1 must not be null")
    @field:Size(min = 3, max = 10, message = "field1 must be between 3 and 10 characters")
    var field1: String? = null,

    @field:NotEmpty(message = "field2 must not be empty")
    var field2: String? = null
)