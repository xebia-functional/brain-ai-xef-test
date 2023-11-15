/**
 * Please note: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */
@file:Suppress("ArrayInDataClass", "EnumEntryName", "RemoveRedundantQualifierName", "UnusedImport")

package com.xebia.functional.openai.models

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Details on the tool outputs needed for this run to continue.
 *
 * @param toolCalls A list of the relevant tool calls.
 */
@Serializable
data class RunObjectRequiredActionSubmitToolOutputs(

  /* A list of the relevant tool calls. */
  @SerialName(value = "tool_calls")
  @Required
  val toolCalls: kotlin.collections.List<RunToolCallObject>
)
