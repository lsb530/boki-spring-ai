package com.boki.bokispringai.prop

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Hello(
    @Value("\${spring.ai.openai.api-key}")
    private val apiKey: String,
) {
    @PostConstruct
    fun init() {
        println("Hello1")
        println("OpenAI API Key: $apiKey")
    }
}