package com.xebia.functional.xef.reasoning.filesystem

import com.xebia.functional.xef.conversation.Conversation
import com.xebia.functional.xef.llm.ChatWithFunctions
import com.xebia.functional.xef.reasoning.tools.Tool

class Files
@JvmOverloads
constructor(
  private val model: ChatWithFunctions,
  private val scope: Conversation,
  private val instructions: List<String> = emptyList(),
  @JvmField val readFile: ReadFile = ReadFile(model, scope),
  @JvmField val writeToTextFile: ProduceTextFile = ProduceTextFile(model, scope, instructions),
) {

  val tools: List<Tool> =
    listOf(
      readFile,
      // createPythonScript,
      writeToTextFile,
    )

  companion object {
    @JvmStatic
    fun create(
      model: ChatWithFunctions,
      scope: Conversation,
      instructions: List<String>,
    ): Files = Files(model, scope, instructions)
  }
}
