package com.example.sadapayassignment

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "SadaPay Test API", version = "1.0", description = "API playground for SadaPay"))
class SadapayAssignmentApplication

fun main(args: Array<String>) {
    runApplication<SadapayAssignmentApplication>(*args)
}