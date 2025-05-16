package com.boki.bokispringai.chat_completion

import org.springframework.ai.chat.client.ChatClient
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean

// @Component
class ChatRunner {

    @Bean
    fun runner(builder: ChatClient.Builder): CommandLineRunner {
        println("runner")
        return CommandLineRunner { args: Array<String?>? ->
            val chatClient = builder.build()
            val response = chatClient.prompt("Tell me a joke").call().content()
            println(response)
        }
    }

}