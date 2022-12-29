package com.example.sadapayassignment

import org.assertj.core.api.AssertionsForInterfaceTypes.*
import org.junit.jupiter.api.Test

class SadapayAssignmentApplicationTests : AbstractContextTest() {

    @Test
    fun contextLoads() {
        assertThat(context).isNotNull
        assertThat(context.beanDefinitionCount).isGreaterThan(0)
    }
}