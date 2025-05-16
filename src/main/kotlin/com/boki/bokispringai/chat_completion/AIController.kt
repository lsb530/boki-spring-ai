package com.boki.bokispringai.chat_completion

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/ai")
@RestController
class AIController(
    private val aiService: AIService
) {

    @PostMapping
    fun chatbot(@RequestBody request: Request): String? {
        return aiService.chatbotDoctor(request)
    }

}