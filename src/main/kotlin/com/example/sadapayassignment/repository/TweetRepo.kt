package com.example.sadapayassignment.repository

import com.example.sadapayassignment.model.Tweet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TweetRepo : JpaRepository<Tweet, Long>