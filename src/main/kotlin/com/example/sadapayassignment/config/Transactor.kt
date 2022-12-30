package com.example.sadapayassignment.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

@Configuration
class Transactor {

    @Autowired
    lateinit var transactionManager: PlatformTransactionManager

    fun <T> inTransaction(func: () -> T): T? {
        val transactionTemplate = TransactionTemplate(transactionManager)
        return transactionTemplate.execute {
            func()
        }
    }

}