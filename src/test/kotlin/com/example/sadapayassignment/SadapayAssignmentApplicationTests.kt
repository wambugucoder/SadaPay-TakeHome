package com.example.sadapayassignment

import org.assertj.core.api.AssertionsForInterfaceTypes.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

class SadapayAssignmentApplicationTests : BaseTest() {

    @Autowired
    lateinit var context: ApplicationContext

    @Test
    fun contextLoads() {
        assertThat(context).isNotNull
        assertThat(context.beanDefinitionCount).isGreaterThan(0)
    }
}