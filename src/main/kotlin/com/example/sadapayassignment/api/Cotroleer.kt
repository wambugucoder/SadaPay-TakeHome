package com.example.sadapayassignment.api

import com.example.sadapayassignment.model.Tweet
import com.example.sadapayassignment.model.User
import com.example.sadapayassignment.repository.TweetRepo
import com.example.sadapayassignment.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Cotroleer {

    @Autowired
    lateinit var userRepository: UserRepo

    @Autowired
    lateinit var tweetRepo: TweetRepo
    @GetMapping(value = ["/api/v1"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun sayHello(): Tweet {
        val user = User(name = "John", age = 30)
        val savedUser = userRepository.save(user.copy())
        val tweets = Tweet(tweetContext = "adad", user = savedUser.copy())
        return tweetRepo.save(tweets)
    }
}