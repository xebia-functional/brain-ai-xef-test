package wikipedia

import com.xebia.functional.xef.conversation.Conversation
import com.xebia.functional.xef.llm.Chat
import com.xebia.functional.xef.prompt.Prompt
import com.xebia.functional.xef.prompt.templates.system
import com.xebia.functional.xef.reasoning.tools.Tool
import kotlin.jvm.JvmSynthetic

interface TestSearchWikipediaByTitleTool : Tool {

  val model: Chat
  val scope: Conversation
  val client: TestWikipediaByTitleAndPageIdClient

  override val name: String
    get() = "SearchWikipediaByTitle"

  override val description: String
    get() =
      "Search secondary tool in Wikipedia for detail information. The tool input is the title of the page, this tool can only be used with valid Wikipedia page titles returned by the primary search tool"

  @JvmSynthetic
  override suspend fun invoke(input: String): String {

    val docs = client.searchByTitle(TestWikipediaByTitleAndPageIdClient.SearchDataByTitle(input))
    return model
      .promptMessages(
        prompt =
          Prompt {
            +system("Search results:")
            +system("Title: ${docs.title}")
            +system("PageId: ${docs.pageId}")
            +system("Content: ${docs.document}")
          },
        scope = scope,
      )
      .firstOrNull()
      ?: "No results found"
  }

  fun close() {
    client.close()
  }
}
