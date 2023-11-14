package com.xebia.functional.xef.conversation

import com.xebia.functional.openai.models.ChatCompletionRole
import com.xebia.functional.openai.models.ext.chat.ChatCompletionRequestMessage.*
import com.xebia.functional.openai.models.ext.chat.ChatCompletionRequestUserMessageContent
import com.xebia.functional.tokenizer.ModelType
import com.xebia.functional.xef.data.*
import com.xebia.functional.xef.metrics.LogsMetric
import com.xebia.functional.xef.prompt.Prompt
import com.xebia.functional.xef.prompt.configuration.PromptConfiguration
import com.xebia.functional.xef.prompt.templates.assistant
import com.xebia.functional.xef.prompt.templates.system
import com.xebia.functional.xef.prompt.templates.user
import com.xebia.functional.xef.store.ConversationId
import com.xebia.functional.xef.store.LocalVectorStore
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.uuid.UUID
import kotlinx.uuid.generateUUID

class ConversationSpec :
  StringSpec({
    "memories should have the correct size in the vector store" {
      val conversationId = ConversationId(UUID.generateUUID().toString())

      val model = TestChatModel(modelType = ModelType.ADA)

      val scope =
        Conversation(
          LocalVectorStore(TestEmbeddings()),
          LogsMetric(),
          conversationId = conversationId
        )

      val vectorStore = scope.store

      model.promptMessages(prompt = Prompt("question 1"), scope = scope)

      model.promptMessages(prompt = Prompt("question 2"), scope = scope)

      val memories = vectorStore.memories(model, conversationId, 10000)

      memories.size shouldBe 4
    }

    """"
      | ADA model has 2049 max context length
      | when the number of token in the conversation is greater than
      | the space allotted for the message history in the prompt configuration
      | the number of messages in the request must have fewer messages than
      | the total number of messages in the conversation
      |""" {
      val messages = generateRandomMessages(50, 40, 60)
      val conversationId = ConversationId(UUID.generateUUID().toString())
      val scope =
        Conversation(
          LocalVectorStore(TestEmbeddings()),
          LogsMetric(),
          conversationId = conversationId
        )
      val vectorStore = scope.store

      val modelAda = TestChatModel(modelType = ModelType.ADA, responses = messages)

      val totalTokens =
        modelAda.tokensFromMessages(
          messages.flatMap {
            listOf(
              ChatCompletionRequestUserMessage(content = ChatCompletionRequestUserMessageContent.TextContent(it.key)),
              ChatCompletionRequestAssistantMessage(content = it.value),
            )
          }
        )

      messages.forEach { message ->
        modelAda.promptMessages(prompt = Prompt(message.key), scope = scope)
      }

      val lastRequest = modelAda.requests.last()

      val memories = vectorStore.memories(modelAda, conversationId, totalTokens)

      // The messages in the request doesn't contain the message response
      val messagesSizePlusMessageResponse = lastRequest.messages.size + 1

      messagesSizePlusMessageResponse shouldBeLessThan memories.size
    }

    """"
      | GPT Turbo 16K model has 16388 max context length
      | when the number of token in the conversation is less than
      | the space allotted for the message history in the prompt configuration
      | the request must send all messages in the conversation
      |""" {
      val messages = generateRandomMessages(50, 40, 60)
      val conversationId = ConversationId(UUID.generateUUID().toString())
      val scope =
        Conversation(
          LocalVectorStore(TestEmbeddings()),
          LogsMetric(),
          conversationId = conversationId
        )
      val vectorStore = scope.store

      val modelGPTTurbo16K =
        TestChatModel(modelType = ModelType.GPT_3_5_TURBO_16_K, responses = messages)

      val totalTokens =
        modelGPTTurbo16K.tokensFromMessages(
          messages.flatMap {
            listOf(
              ChatCompletionRequestUserMessage(content = ChatCompletionRequestUserMessageContent.TextContent(it.key)),
              ChatCompletionRequestAssistantMessage(content = it.value)
            )
          }
        )

      messages.forEach { message ->
        modelGPTTurbo16K.promptMessages(prompt = Prompt(message.key), scope = scope)
      }

      val lastRequest = modelGPTTurbo16K.requests.last()

      val memories = vectorStore.memories(modelGPTTurbo16K, conversationId, totalTokens)

      // The messages in the request doesn't contain the message response
      val messagesSizePlusMessageResponse = lastRequest.messages.size + 1

      messagesSizePlusMessageResponse shouldBe memories.size
    }

    "functionCall shouldn't be null when the model support functions and the prompt contain a function" {
      val question = "fake-question"
      val answer = Answer("fake-answer")

      val message = mapOf(question to Json.encodeToString(answer))

      val conversationId = ConversationId(UUID.generateUUID().toString())
      val scope =
        Conversation(
          LocalVectorStore(TestEmbeddings()),
          LogsMetric(),
          conversationId = conversationId
        )

      val model =
        TestFunctionsModel(modelType = ModelType.GPT_3_5_TURBO_FUNCTIONS, responses = message)

      val response: Answer =
        model.prompt(prompt = Prompt(question), scope = scope, serializer = Answer.serializer())

      val lastRequest = model.requests.last()

      response shouldBe answer

      lastRequest.functionCall shouldNotBe null
    }

    "the message of the request should be the JSON string of the question when the prompt contains serializable object" {
      val question = Question("fake-question")
      val questionJsonString = Json.encodeToString(question)
      val answer = Answer("fake-answer")
      val answerJsonString = Json.encodeToString(answer)

      val message = mapOf(questionJsonString to answerJsonString)

      val conversationId = ConversationId(UUID.generateUUID().toString())
      val scope =
        Conversation(
          LocalVectorStore(TestEmbeddings()),
          LogsMetric(),
          conversationId = conversationId
        )

      val model =
        TestFunctionsModel(modelType = ModelType.GPT_3_5_TURBO_FUNCTIONS, responses = message)

      val response: Answer =
        model.prompt(
          prompt = Prompt { +user(question) },
          scope = scope,
          serializer = Answer.serializer()
        )

      val lastRequest = model.requests.last()

      lastRequest.messages.last().contentAsString() shouldBe questionJsonString

      response shouldBe answer
    }

    "the scope's store should contains all the messages" {
      val conversationId = ConversationId(UUID.generateUUID().toString())

      val model = TestChatModel(modelType = ModelType.ADA)

      val scope =
        Conversation(
          LocalVectorStore(TestEmbeddings()),
          LogsMetric(),
          conversationId = conversationId
        )

      val vectorStore = scope.store

      val firstPrompt = Prompt {
        +system("question 1")
        +user("question 2")
      }

      val firstResponse = model.promptMessage(prompt = firstPrompt, scope = scope)
      val aiFirstMessage = ChatCompletionRequestAssistantMessage(content = firstResponse)

      val secondPrompt = Prompt { +user("question 3") }

      val secondResponse = model.promptMessage(prompt = secondPrompt, scope = scope)
      val aiSecondMessage =
        ChatCompletionRequestAssistantMessage(content = secondResponse)

      val thirdPrompt = Prompt {
        +system("question 4")
        +user("question 5")
      }

      val thirdResponse = model.promptMessage(prompt = thirdPrompt, scope = scope)
      val aiThirdMessage =
        ChatCompletionRequestAssistantMessage(content = secondResponse)

      val memories = vectorStore.memories(model, conversationId, 10000)
      val expectedMessages =
        (firstPrompt.messages +
            aiFirstMessage +
            secondPrompt.messages +
            aiSecondMessage +
            thirdPrompt.messages +
            aiThirdMessage)
          .mapNotNull { it.contentAsString() }
      val actualMessages = memories.mapNotNull { it.content.asRequestMessage().contentAsString() }
      actualMessages shouldBe expectedMessages
    }

    "when using 2 different scopes with the same conversationId, the index of the memories should be in order" {
      val conversationId = ConversationId(UUID.generateUUID().toString())

      val model = TestChatModel(modelType = ModelType.ADA)

      val vectorStore = LocalVectorStore(TestEmbeddings())

      val scope1 = Conversation(vectorStore, LogsMetric(), conversationId = conversationId)

      val firstPrompt = Prompt {
        +user("question in scope 1")
        +assistant("answer in scope 1")
      }

      model.promptMessages(prompt = firstPrompt, scope = scope1)

      val scope2 = Conversation(vectorStore, LogsMetric(), conversationId = conversationId)

      val secondPrompt = Prompt {
        +user("question in scope 2")
        +assistant("answer in scope 2")
      }

      model.promptMessages(prompt = secondPrompt, scope = scope2)

      val memories = vectorStore.memories(model, conversationId, 10000)

      memories.map { it.index } shouldBe listOf(1, 2, 3, 4, 5, 6)
    }

    "when using MessagesToHistory.ALL policy, the scope's store should contains all messages" {
      val conversationId = ConversationId(UUID.generateUUID().toString())

      val model = TestChatModel(modelType = ModelType.ADA)

      val vectorStore = LocalVectorStore(TestEmbeddings())

      val scope = Conversation(vectorStore, LogsMetric(), conversationId = conversationId)

      val prompt =
        Prompt {
            +system("system message")
            +user("question in scope 1")
          }
          .copy(
            configuration =
              PromptConfiguration {
                messagePolicy { addMessagesToConversation = MessagesToHistory.ALL }
              }
          )

      model.promptMessages(prompt = prompt, scope = scope)

      val messagesStored = scope.store.memories(model, conversationId, Int.MAX_VALUE)

      messagesStored.size shouldBe 3
    }

    "when using MessagesToHistory.ONLY_SYSTEM_MESSAGES policy, the scope's store should contains only system messages" {
      val conversationId = ConversationId(UUID.generateUUID().toString())

      val model = TestChatModel(modelType = ModelType.ADA)

      val vectorStore = LocalVectorStore(TestEmbeddings())

      val scope = Conversation(vectorStore, LogsMetric(), conversationId = conversationId)

      val prompt =
        Prompt {
            +system("system message")
            +user("question in scope 1")
          }
          .copy(
            configuration =
              PromptConfiguration {
                messagePolicy { addMessagesToConversation = MessagesToHistory.ONLY_SYSTEM_MESSAGES }
              }
          )

      model.promptMessages(prompt = prompt, scope = scope)

      val messagesStored = scope.store.memories(model, conversationId, Int.MAX_VALUE)

      messagesStored.filter { it.content.role == ChatCompletionRole.system } shouldBe messagesStored
    }

    "when using MessagesToHistory.NOT_SYSTEM_MESSAGES policy, the scope's store shouldn't contains system messages" {
      val conversationId = ConversationId(UUID.generateUUID().toString())

      val model = TestChatModel(modelType = ModelType.ADA)

      val vectorStore = LocalVectorStore(TestEmbeddings())

      val scope = Conversation(vectorStore, LogsMetric(), conversationId = conversationId)

      val prompt =
        Prompt {
            +system("system message")
            +user("question in scope 1")
          }
          .copy(
            configuration =
              PromptConfiguration {
                messagePolicy { addMessagesToConversation = MessagesToHistory.NOT_SYSTEM_MESSAGES }
              }
          )

      model.promptMessages(prompt = prompt, scope = scope)

      val messagesStored = scope.store.memories(model, conversationId, Int.MAX_VALUE)

      messagesStored.filter { it.content.role != ChatCompletionRole.system } shouldBe messagesStored
    }

    "when using MessagesToHistory.NONE policy, the scope's store shouldn't contains messages" {
      val conversationId = ConversationId(UUID.generateUUID().toString())

      val model = TestChatModel(modelType = ModelType.ADA)

      val vectorStore = LocalVectorStore(TestEmbeddings())

      val scope = Conversation(vectorStore, LogsMetric(), conversationId = conversationId)

      val prompt =
        Prompt {
            +system("system message")
            +user("question in scope 1")
          }
          .copy(
            configuration =
              PromptConfiguration {
                messagePolicy { addMessagesToConversation = MessagesToHistory.NONE }
              }
          )

      model.promptMessages(prompt = prompt, scope = scope)

      val messagesStored =
        scope.store.memories(model, conversationId, Int.MAX_VALUE).filter {
          it.content.role == ChatCompletionRole.system
        }

      messagesStored.size shouldBe 0
    }

    "when using MessagesFromHistory.ALL policy, the request should contains the previous messages in the conversation" {
      val conversationId = ConversationId(UUID.generateUUID().toString())

      val model = TestChatModel(modelType = ModelType.ADA)

      val vectorStore = LocalVectorStore(TestEmbeddings())

      val scope = Conversation(vectorStore, LogsMetric(), conversationId = conversationId)

      val firstPrompt = Prompt {
        +system("system message")
        +user("question in scope 1")
      }

      model.promptMessages(prompt = firstPrompt, scope = scope)

      val secondPrompt =
        Prompt {
            +user("question in scope 2")
            +assistant("answer in scope 2")
            +user("question in scope 3")
          }
          .copy(
            configuration =
              PromptConfiguration {
                messagePolicy { addMessagesFromConversation = MessagesFromHistory.ALL }
              }
          )

      model.promptMessages(prompt = secondPrompt, scope = scope)

      model.requests.last().messages.size shouldBe 6
    }

    "when using MessagesFromHistory.NONE policy, the request shouldn't contains the previous messages in the conversation" {
      val conversationId = ConversationId(UUID.generateUUID().toString())

      val model = TestChatModel(modelType = ModelType.ADA)

      val vectorStore = LocalVectorStore(TestEmbeddings())

      val scope = Conversation(vectorStore, LogsMetric(), conversationId = conversationId)

      val firstPrompt = Prompt {
        +system("system message")
        +user("question in scope 1")
      }

      model.promptMessages(prompt = firstPrompt, scope = scope)

      val secondPrompt =
        Prompt {
            +user("question in scope 2")
            +assistant("answer in scope 2")
            +user("question in scope 3")
          }
          .copy(
            configuration =
              PromptConfiguration {
                messagePolicy { addMessagesFromConversation = MessagesFromHistory.NONE }
              }
          )

      model.promptMessages(prompt = secondPrompt, scope = scope)

      model.requests.last().messages.size shouldBe 3
    }
  })
