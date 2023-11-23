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
  val codeInterpreter: RunStepDetailsToolCallsCodeObjectCodeInterpreter? = null,

  /* For now, this is always going to be an empty object. */
  @SerialName(value = "retrieval") val retrieval: kotlin.String? = null,
  @SerialName(value = "function")
  val function: RunStepDetailsToolCallsFunctionObjectFunction? = null
) {

  /**
   * The type of tool call. This is always going to be `code_interpreter` for this type of tool
   * call.
   *
   * Values: code_interpreter,retrieval,function
   */
  @Serializable
  enum class Type(val value: kotlin.String) {
    @SerialName(value = "code_interpreter") code_interpreter("code_interpreter"),
    @SerialName(value = "retrieval") retrieval("retrieval"),
    @SerialName(value = "function") function("function")
  }
}
