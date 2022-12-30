package com.example.sadapayassignment.utils

import com.example.sadapayassignment.exception.InvalidArgumentException
import com.google.common.base.Strings
import java.lang.String.*

fun checkRequired(value: String?, name: String) {
    if (Strings.isNullOrEmpty(value)) {
        throw InvalidArgumentException(format("%s is required", name))
    }
}