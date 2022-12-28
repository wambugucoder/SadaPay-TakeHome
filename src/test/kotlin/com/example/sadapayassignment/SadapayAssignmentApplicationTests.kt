package com.example.sadapayassignment

import org.assertj.core.api.AssertionsForInterfaceTypes.*
import org.junit.jupiter.api.Test

class SadapayAssignmentApplicationTests : AbstractContextTest() {

    @Test
    fun contextLoads() {
        // Load the application contex

        // Verify that the context loads successfully
        assertThat(context).isNotNull
        assertThat(context.beanDefinitionCount).isGreaterThan(0)
    }
}