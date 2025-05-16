package com.boki.bokispringai.chat_completion

import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.prompt.PromptTemplate
import org.springframework.ai.content.Media
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.core.io.ByteArrayResource
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils
import org.springframework.web.multipart.MultipartFile
import java.net.URI

@Service
class AIService(
    private val openAiChatModel: OpenAiChatModel,
    private val chatModel: ChatModel
) {

    fun chatbotDoctor(request: Request): String? {
        val customPromptTemplate = PromptTemplate(
            """당신은 전문 의사입니다. 그래서 환자의 증상을 바탕으로 환자의 병을 예측합니다.
                |환자가 자신의 증상을 말하면 본인의 지식을 활용해 최대한 자세하고 친절하게 알려주세요.
                |"환자: 배가 너무 아파요."
                |"답변: 배탈인 것 같습니다."
                |"환자: 머리가 너무 아파요."
                |"답변: 두통인 것 같습니다."
                |"환자: 기침을 너무 해요."
                |"답변: 감기인 것 같습니다."
                |이런 형식으로 대답해줘.
                |"답변: 기침을 하는 것은 여러 원인에 의해 발생할 수 있습니다. 일반적으로 감기나 독감과 같은 호흡기 감염이 원인기침이 발생할 수 있습니다. 기침이 얼마나 오래 지속되었는지, 다른 증상(예: 발열, 가래, 호흡 곤란 등)이 있는지 문하여 전문의의 진료를 받는 것이 좋습니다." 
                |이런식으로 작성하지 말고 내 앞서 말한 예시대로 짧게 말해줘
                |추가로 맨 앞에 (답변: ) 과 같은 맥락은 빼줘
                |"환자: {message}"
            """.trimMargin()
        )
        // chatModel.call(Prompt(
        //     "hello",
        //     OpenAiChatOptions.builder()
        //         .model("gpt-4o-mini")
        //         .temperature(0.7)
        //         .build()
        // ))

        val simplePromptTemplate = PromptTemplate(
            """당신은 전문 의사입니다. 그래서 환자의 증상을 바탕으로 환자의 병을 예측합니다.
                |환자가 자신의 증상을 말하면 본인의 지식을 활용해 최대한 자세하고 친절하게 알려주세요.
                |"환자: {message}"
            """.trimMargin()
        )

        val prompt = simplePromptTemplate.create(mapOf("message" to request.text))

        // openAiChatModel.call(request.text)
        val output = openAiChatModel.call(prompt).result.output
        println(output.text)
        return output.text
    }

    fun analyzeImageUrl(request: Request): String? {
        // 사과: https://img.freepik.com/premium-vector/realistic-apple-illustration-design-free_555017-18.jpg
        // 바나나: https://img.freepik.com/free-vector/sticker-design-with-a-banana-isolated_1308-77292.jpg
        val prompt = "너는 음식 감별사야. 사진을 보고, 이 사진으로 만들 수 있는 요리를 알려줘."
        val userMessage = UserMessage.builder()
            // .text("이 사진이 보이나요?")
            .text(prompt)
            .media(Media(MimeTypeUtils.IMAGE_JPEG, URI.create(request.text)))
            .build()
        val response = openAiChatModel.call(userMessage)
        println(response)
        return response
    }

    fun analyzeImageUrlWithRequest(url: String, request: String): String? {
        val userMessage = UserMessage.builder()
            .text(request)
            .media(Media(MimeTypeUtils.IMAGE_JPEG, URI.create(url)))
            .build()
        val response = openAiChatModel.call(userMessage)
        println(response)
        return response
    }

    fun analyzeImage(file: MultipartFile): String? {
        // println(file.originalFilename)
        val resource = ByteArrayResource(file.bytes)
        val mimeType = (file.contentType
            ?.let { MimeTypeUtils.parseMimeType(it) }
            ?: MimeTypeUtils.IMAGE_JPEG)

        val prompt = "너는 음식 감별사야. 사진을 보고, 이 사진으로 만들 수 있는 요리를 알려줘."
        val userMessage = UserMessage.builder()
            // .text("이 사진이 보이나요?")
            .text(prompt)
            .media(Media(mimeType, resource))
            .build()

        val response = chatModel.call(userMessage)
        println(response)
        return response
    }

}