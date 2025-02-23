package com.xebia.functional.xef.conversation

import com.xebia.functional.xef.AIError
import com.xebia.functional.xef.llm.*
import com.xebia.functional.xef.llm.models.functions.CFunction
import com.xebia.functional.xef.llm.models.images.ImagesGenerationResponse
import com.xebia.functional.xef.metrics.Metric
import com.xebia.functional.xef.prompt.Prompt
import com.xebia.functional.xef.store.ConversationId
import com.xebia.functional.xef.store.VectorStore
import kotlin.jvm.JvmSynthetic
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.KSerializer
import kotlinx.uuid.UUID
import kotlinx.uuid.generateUUID

interface Conversation : AutoClose, AutoCloseable {

  val store: VectorStore

  val metric: Metric

  val conversationId: ConversationId?

  val conversation: Conversation

  @AiDsl
  @JvmSynthetic
  suspend fun addContext(vararg docs: String) {
    store.addTexts(docs.toList())
  }

  @AiDsl
  @JvmSynthetic
  suspend fun addContext(docs: Iterable<String>): Unit {
    store.addTexts(docs.toList())
  }

  @AiDsl
  @JvmSynthetic
  suspend fun <A> ChatWithFunctions.prompt(prompt: Prompt, serializer: KSerializer<A>): A =
    prompt(prompt, conversation, serializer)

  @AiDsl
  @JvmSynthetic
  suspend fun <A> ChatWithFunctions.prompt(
    prompt: Prompt,
    function: CFunction,
    serializer: (String) -> A
  ): A = prompt(prompt, conversation, function, serializer)

  @AiDsl
  @JvmSynthetic
  suspend fun Chat.promptMessage(
    prompt: Prompt,
  ): String = promptMessages(prompt, conversation).firstOrNull() ?: throw AIError.NoResponse()

  @AiDsl
  @JvmSynthetic
  suspend fun Chat.promptMessages(prompt: Prompt): List<String> =
    promptMessages(prompt, conversation)

  @AiDsl
  fun Chat.promptStreaming(prompt: Prompt): Flow<String> = promptStreaming(prompt, conversation)

  /**
   * Run a [prompt] describes the images you want to generate within the context of [Conversation].
   * Returns a [ImagesGenerationResponse] containing time and urls with images generated.
   *
   * @param prompt a [Prompt] describing the images you want to generate.
   * @param numberImages number of images to generate.
   * @param size the size of the images to generate.
   */
  @AiDsl
  @JvmSynthetic
  suspend fun Images.images(
    prompt: Prompt,
    numberImages: Int = 1,
    size: String = "1024x1024"
  ): ImagesGenerationResponse = images(prompt, store, numberImages, size)

  companion object {

    operator fun invoke(
      store: VectorStore,
      metric: Metric,
      conversationId: ConversationId? = ConversationId(UUID.generateUUID().toString())
    ): PlatformConversation = PlatformConversation.create(store, metric, conversationId)

    @JvmSynthetic
    suspend operator fun <A> invoke(
      store: VectorStore,
      metric: Metric,
      conversationId: ConversationId? = ConversationId(UUID.generateUUID().toString()),
      block: suspend PlatformConversation.() -> A
    ): A = block(invoke(store, metric, conversationId))
  }
}
