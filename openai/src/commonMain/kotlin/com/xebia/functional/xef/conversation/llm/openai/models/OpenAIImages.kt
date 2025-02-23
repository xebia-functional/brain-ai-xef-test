package com.xebia.functional.xef.conversation.llm.openai.models

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.image.ImageSize
import com.aallam.openai.api.image.imageCreation
import com.xebia.functional.tokenizer.ModelType
import com.xebia.functional.xef.conversation.llm.openai.OpenAI
import com.xebia.functional.xef.llm.Images
import com.xebia.functional.xef.llm.models.chat.Message
import com.xebia.functional.xef.llm.models.images.ImageGenerationUrl
import com.xebia.functional.xef.llm.models.images.ImagesGenerationRequest
import com.xebia.functional.xef.llm.models.images.ImagesGenerationResponse

class OpenAIImages(
  private val provider: OpenAI, // TODO: use context receiver
  override val modelType: ModelType,
) : Images {

  private val client = provider.defaultClient

  override fun copy(modelType: ModelType) = OpenAIImages(provider, modelType)

  @OptIn(BetaOpenAI::class)
  override suspend fun createImages(request: ImagesGenerationRequest): ImagesGenerationResponse {
    val clientRequest: ImageCreation = imageCreation {
      prompt = request.prompt.messages.firstOrNull()?.content
      n = request.numberImages
      size = ImageSize(request.size)
      user = request.user
    }
    val response = client.imageURL(clientRequest)
    return ImagesGenerationResponse(data = response.map { ImageGenerationUrl(it.url) })
  }

  override fun tokensFromMessages(messages: List<Message>): Int {
    TODO("Not yet implemented")
  }
}
