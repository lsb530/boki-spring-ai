package com.boki.bokispringai.prop

import org.springframework.stereotype.Component

@Component
class Hello3(
    private val props: OpenAIProperties
) {
    init {
        // println("Hello3")
        // println("OpenAI API Key: ${props.apiKey}")
    }
}