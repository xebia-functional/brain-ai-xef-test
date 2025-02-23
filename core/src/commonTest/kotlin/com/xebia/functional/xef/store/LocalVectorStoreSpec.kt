package com.xebia.functional.xef.store

import com.xebia.functional.tokenizer.ModelType
import com.xebia.functional.xef.data.TestEmbeddings
import com.xebia.functional.xef.data.TestModel
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class LocalVectorStoreSpec :
  StringSpec({
    "memories function should return all of messages in the right order when the limit is greater than the number of stored messages" {
      val model = TestModel(modelType = ModelType.ADA)
      val localVectorStore = LocalVectorStore(TestEmbeddings())

      val memoryData = MemoryData()

      val messages1 = memoryData.generateRandomMessages(4)
      val messages2 = memoryData.generateRandomMessages(3)

      localVectorStore.addMemories(messages1)
      localVectorStore.addMemories(messages2)

      val messages =
        localVectorStore.memories(model, memoryData.defaultConversationId, Int.MAX_VALUE)

      val messagesExpected = messages1 + messages2

      messages shouldBe messagesExpected
    }

    "memories function should return the last n messages in the right order" {
      val model = TestModel(modelType = ModelType.ADA)
      val localVectorStore = LocalVectorStore(TestEmbeddings())

      val memoryData = MemoryData()

      val messages1 = memoryData.generateRandomMessages(4)
      val messages2 = memoryData.generateRandomMessages(3)

      val tokensForMessages2 = model.tokensFromMessages(messages2.map { it.content })

      localVectorStore.addMemories(messages1)
      localVectorStore.addMemories(messages2)

      val messages =
        localVectorStore.memories(model, memoryData.defaultConversationId, tokensForMessages2)

      messages shouldBe messages2
    }

    "memories function should return the last n messages in the right order for a specific conversation id" {
      val model = TestModel(modelType = ModelType.ADA)
      val localVectorStore = LocalVectorStore(TestEmbeddings())

      val firstId = ConversationId("first-id")
      val secondId = ConversationId("second-id")

      val memoryData = MemoryData()

      val messages1 = memoryData.generateRandomMessages(4, conversationId = firstId)
      val messages2 = memoryData.generateRandomMessages(3, conversationId = secondId)

      localVectorStore.addMemories(messages1 + messages2)

      val tokensForMessages1 = model.tokensFromMessages(messages1.map { it.content })
      val tokensForMessages2 = model.tokensFromMessages(messages2.map { it.content })

      val messagesFirstId = localVectorStore.memories(model, firstId, tokensForMessages1)

      val messagesSecondId = localVectorStore.memories(model, secondId, tokensForMessages2)

      messagesFirstId shouldBe messages1
      messagesSecondId shouldBe messages2
    }
  })
