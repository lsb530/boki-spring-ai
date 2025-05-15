package com.boki.bokispringai

import com.boki.bokispringai.prop.Hello2
import com.boki.bokispringai.prop.OpenAIProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(value = [Hello2::class, OpenAIProperties::class])
@SpringBootApplication
class BokiSpringAiApplication

fun main(args: Array<String>) {
    runApplication<BokiSpringAiApplication>(*args)
}
