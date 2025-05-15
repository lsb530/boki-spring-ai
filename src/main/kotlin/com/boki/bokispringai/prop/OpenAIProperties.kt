package com.boki.bokispringai.prop

import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.stereotype.Component

// @ConfigurationProperties(prefix = "spring.ai.openai")
// @Component
@PropertyPath("spring.ai.openai")
data class OpenAIProperties @ConstructorBinding constructor(
    val apiKey: String
)