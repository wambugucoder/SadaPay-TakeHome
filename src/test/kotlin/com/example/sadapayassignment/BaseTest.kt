package com.example.sadapayassignment

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@ContextConfiguration(initializers = [TestContainer::class])
@TestPropertySource(locations = ["classpath:application-test.properties"])
abstract class BaseTest