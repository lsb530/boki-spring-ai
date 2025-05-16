package com.boki.bokispringai.chat_completion

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

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

    @PostMapping("/image3")
    fun imageFile2AI(@RequestPart("file") file: MultipartFile): String? {
        return aiService.analyzeImage(file)
    }

    @PostMapping("/image4")
    fun imageFile2AIWithRequest(
        @RequestPart("file") file: MultipartFile,
        @RequestPart("request") request: String
    ): String? {
        return aiService.analyzeImageWithRequest(file, request)
    }

}