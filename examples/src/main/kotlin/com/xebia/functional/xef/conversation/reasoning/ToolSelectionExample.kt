package com.xebia.functional.xef.conversation.reasoning

import com.xebia.functional.xef.conversation.llm.openai.OpenAI
import com.xebia.functional.xef.reasoning.filesystem.Files
import com.xebia.functional.xef.reasoning.pdf.PDF
import com.xebia.functional.xef.reasoning.text.Text
import com.xebia.functional.xef.reasoning.tools.ToolSelection

suspend fun main() {
  OpenAI.conversation {
    val model = OpenAI.fromEnvironment().DEFAULT_CHAT
    val serialization = OpenAI.fromEnvironment().DEFAULT_SERIALIZATION
    val text = Text(model = model, scope = this)
    val files = Files(model = serialization, scope = this)
    val pdf = PDF(chat = model, model = serialization, scope = this)

    val toolSelection =
      ToolSelection(
        model = serialization,
        scope = this,
        tools =
          listOf(
            text.summarize,
            pdf.readPDFFromUrl,
            files.readFile,
            files.writeToTextFile,
          ),
      )

    val result =
      toolSelection.applyInferredTools(
        """|
    |Extract information from https://arxiv.org/pdf/2305.10601.pdf
  """
          .trimMargin()
      )
    println(result)
  }
}
