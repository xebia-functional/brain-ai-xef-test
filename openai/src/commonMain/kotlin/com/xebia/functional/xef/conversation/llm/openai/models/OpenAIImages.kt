package com.xebia.functional.xef.conversation.llm.openai.models

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.image.ImageSize
import com.aallam.openai.api.image.imageCreation
import com.aallam.openai.client.OpenAI
import com.xebia.functional.tokenizer.EncodingType
import com.xebia.functional.xef.llm.Images
import com.xebia.functional.xef.llm.models.images.ImageGenerationUrl
import com.xebia.functional.xef.llm.models.images.ImagesGenerationRequest
import com.xebia.functional.xef.llm.models.images.ImagesGenerationResponse

class OpenAIImages(
  override val modelID: com.xebia.functional.xef.llm.models.ModelID,
  private val client: OpenAI,
  override val encodingType: EncodingType,
) : Images, OpenAIModel {
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
}
