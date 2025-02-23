package com.xebia.functional.xef.llm

import com.xebia.functional.xef.AIError
import com.xebia.functional.xef.conversation.AiDsl
import com.xebia.functional.xef.conversation.Conversation
import com.xebia.functional.xef.llm.models.chat.*
import com.xebia.functional.xef.prompt.Prompt
import com.xebia.functional.xef.prompt.templates.assistant
import kotlinx.coroutines.flow.*

interface Chat : LLM {

  suspend fun createChatCompletion(request: ChatCompletionRequest): ChatCompletionResponse

  suspend fun createChatCompletions(request: ChatCompletionRequest): Flow<ChatCompletionChunk>

  @AiDsl
  fun promptStreaming(prompt: Prompt, scope: Conversation): Flow<String> = flow {
    val messagesForRequestPrompt =
      PromptCalculator.adaptPromptToConversationAndModel(prompt, scope, this@Chat)

    val request =
      ChatCompletionRequest(
        user = prompt.configuration.user,
        messages = messagesForRequestPrompt.messages,
        n = prompt.configuration.numberOfPredictions,
        temperature = prompt.configuration.temperature,
        maxTokens = prompt.configuration.minResponseTokens,
        streamToStandardOut = true
      )

    createChatCompletions(request)
      .mapNotNull { it.choices.mapNotNull { it.delta?.content }.reduceOrNull(String::plus) }
      .onEach { emit(it) }
      .fold("", String::plus)
      .also { finalText ->
        val aiResponseMessage = assistant(finalText)
        val newMessages = prompt.messages + listOf(aiResponseMessage)
        newMessages.addToMemory(scope, prompt.configuration.messagePolicy.addMessagesToConversation)
      }
  }

  @AiDsl
  suspend fun promptMessage(prompt: Prompt, scope: Conversation): String =
    promptMessages(prompt, scope).firstOrNull() ?: throw AIError.NoResponse()

  @AiDsl
  suspend fun promptMessages(prompt: Prompt, scope: Conversation): List<String> =
    scope.metric.promptSpan(prompt) {
      val promptMemories = prompt.messages.toMemory(scope)
      val adaptedPrompt =
        PromptCalculator.adaptPromptToConversationAndModel(prompt, scope, this@Chat)

      adaptedPrompt.addMetrics(scope)

      val request =
        ChatCompletionRequest(
          user = adaptedPrompt.configuration.user,
          messages = adaptedPrompt.messages,
          n = adaptedPrompt.configuration.numberOfPredictions,
          temperature = adaptedPrompt.configuration.temperature,
          maxTokens = adaptedPrompt.configuration.minResponseTokens,
        )

      createChatCompletion(request)
        .addMetrics(scope)
        .choices
        .addChoiceToMemory(
          scope,
          promptMemories,
          prompt.configuration.messagePolicy.addMessagesToConversation
        )
        .mapNotNull { it.message?.content }
    }
}
