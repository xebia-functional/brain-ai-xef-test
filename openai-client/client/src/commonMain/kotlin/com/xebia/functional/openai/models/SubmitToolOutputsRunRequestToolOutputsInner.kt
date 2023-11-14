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
 * @param toolCallId The ID of the tool call in the `required_action` object within the run object
 *   the output is being submitted for.
 * @param output The output of the tool call to be submitted to continue the run.
 */
@Serializable
data class SubmitToolOutputsRunRequestToolOutputsInner(

  /* The ID of the tool call in the `required_action` object within the run object the output is being submitted for. */
  @SerialName(value = "tool_call_id") val toolCallId: kotlin.String? = null,

  /* The output of the tool call to be submitted to continue the run. */
  @SerialName(value = "output") val output: kotlin.String? = null
)
