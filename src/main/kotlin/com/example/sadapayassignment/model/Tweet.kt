package com.example.sadapayassignment.model

import javax.persistence.*

@Entity
@Table(name = "tweets_table")
data class Tweet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val tweetContext: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)