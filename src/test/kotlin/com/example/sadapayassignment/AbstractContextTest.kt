package com.example.sadapayassignment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

abstract class AbstractContextTest : BaseTest() {

    @Autowired
    lateinit var context: ApplicationContext
}