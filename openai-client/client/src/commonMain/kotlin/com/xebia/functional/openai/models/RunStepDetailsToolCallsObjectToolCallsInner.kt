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
 * @param id The ID of the tool call object.
 * @param type The type of tool call. This is always going to be `code_interpreter` for this type of
 *   tool call.
 * @param codeInterpreter
 * @param retrieval For now, this is always going to be an empty object.
 * @param function
 */
@Serializable
data class RunStepDetailsToolCallsObjectToolCallsInner(

  /* The ID of the tool call object. */
  @SerialName(value = "id") @Required val id: kotlin.String,

  /* The type of tool call. This is always going to be `code_interpreter` for this type of tool call. */
  @SerialName(value = "type") @Required val type: RunStepDetailsToolCallsObjectToolCallsInner.Type,
  @SerialName(value = "code_interpreter")
  @Required
  val codeInterpreter: RunStepDetailsToolCallsCodeObjectCodeInterpreter,

  /* For now, this is always going to be an empty object. */
  @SerialName(value = "retrieval") @Required val retrieval: kotlin.String,
  @SerialName(value = "function")
  @Required
  val function: RunStepDetailsToolCallsFunctionObjectFunction
) {

  /**
   * The type of tool call. This is always going to be `code_interpreter` for this type of tool
   * call.
   *
   * Values: codeInterpreter,retrieval,function
   */
  @Serializable
  enum class Type(val value: kotlin.String) {
    @SerialName(value = "code_interpreter") codeInterpreter("code_interpreter"),
    @SerialName(value = "retrieval") retrieval("retrieval"),
    @SerialName(value = "function") function("function")
  }
}
