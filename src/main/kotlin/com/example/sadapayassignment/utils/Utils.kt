package com.example.sadapayassignment.utils

fun Array<String>.containsAllSpecifiedElements(elements: Array<String>): Boolean {
    return elements.all { this.contains(it) }
}