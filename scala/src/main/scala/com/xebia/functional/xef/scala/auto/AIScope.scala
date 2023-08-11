package com.xebia.functional.xef.scala.auto

import com.xebia.functional.xef.auto.Conversation

final case class AIScope(kt: Conversation)
object AIScope:
  def fromCore(conversation: Conversation): AIScope = new AIScope(conversation)
