package com.xebia.functional.xef.reasoning.wikipedia

import com.xebia.functional.xef.conversation.Conversation
import com.xebia.functional.xef.llm.Chat

actual class SearchWikipediaByPageId
actual constructor(override val model: Chat, override val scope: Conversation) :
  SearchWikipediaByPageIdTool, AutoCloseable {
  override val client: WikipediaClient = WikipediaClient()

  override fun close() {
    client.close()
  }
}
