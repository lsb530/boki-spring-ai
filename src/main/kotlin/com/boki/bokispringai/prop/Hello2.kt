package com.boki.bokispringai.prop

import jakarta.annotation.PostConstruct

// @ConfigurationProperties(prefix = "spring.ai.openai")
@PropertyPath("spring.ai.openai")
class Hello2 {
    // var + lateinit + setter 바인딩
    lateinit var apiKey: String

    @PostConstruct
    fun init() {
        // println("Hello2")
        // println("OpenAI API Key: $apiKey")
    }
}