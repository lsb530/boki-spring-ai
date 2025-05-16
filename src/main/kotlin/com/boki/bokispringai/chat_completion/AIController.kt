package com.boki.bokispringai.chat_completion

import org.springframework.web.bind.annotation.*

@RequestMapping("/ai")
@RestController
class AIController(
    private val aiService: AIService
) {

    @PostMapping
    fun chatbot(@RequestBody request: Request): String? {
        return aiService.chatbotDoctor(request)
    }

    @PostMapping("/image")
    fun imageUrl2AI(@RequestBody request: Request): String? {
        return aiService.analyzeImageUrl(request)
    }

    @PostMapping("/image2")
    fun imageUrl2AIWithRequest(
        @RequestParam("url") url: String,
        @RequestParam("request") request: String,
    ): String? {
        return aiService.analyzeImageUrlWithRequest(url, request)
    }

}