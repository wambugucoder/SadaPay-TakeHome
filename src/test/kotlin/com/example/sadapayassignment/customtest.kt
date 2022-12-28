package com.example.sadapayassignment

import com.example.sadapayassignment.model.Tweet
import com.example.sadapayassignment.model.User
import com.example.sadapayassignment.repository.TweetRepo
import com.example.sadapayassignment.repository.UserRepo
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class customtest : BaseTest() {

    @Autowired
    lateinit var userRepository: UserRepo

    @Autowired
    lateinit var tweetRepo: TweetRepo

    @Test
    fun `test saving a user`() {
        val user = User(name = "John", age = 30)
        val savedUser = userRepository.save(user.copy())
        val tweets = Tweet(tweetContext = "adad", user = savedUser.copy())
        tweetRepo.save(tweets)
        assertIgnoringId(user, userRepository.findAll().single())
    }
}