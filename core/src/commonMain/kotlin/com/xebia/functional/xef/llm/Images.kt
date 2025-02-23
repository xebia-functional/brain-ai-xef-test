package com.xebia.functional.xef.llm

import com.xebia.functional.xef.llm.models.images.ImagesGenerationRequest
import com.xebia.functional.xef.llm.models.images.ImagesGenerationResponse
import com.xebia.functional.xef.prompt.Prompt
import com.xebia.functional.xef.store.VectorStore

interface Images : LLM {
  suspend fun createImages(request: ImagesGenerationRequest): ImagesGenerationResponse

  /**
   * Run a [prompt] describes the images you want to generate within the context of [CoreAIScope].
   * Returns a [ImagesGenerationResponse] containing time and urls with images generated.
   *
   * @param prompt a [Prompt] describing the images you want to generate.
   * @param numberImages number of images to generate.
   * @param size the size of the images to generate.
   */
  suspend fun images(
    prompt: Prompt,
    context: VectorStore,
    numberImages: Int = 1,
    size: String = "1024x1024"
  ): ImagesGenerationResponse {
    val request =
      ImagesGenerationRequest(
        prompt = prompt,
        numberImages = numberImages,
        size = size,
        user = prompt.configuration.user
      )
    return createImages(request)
  }
}
