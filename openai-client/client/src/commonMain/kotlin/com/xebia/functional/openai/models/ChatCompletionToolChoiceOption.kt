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
 * Controls which (if any) function is called by the model. `none` means the model will not call a
 * function and instead generates a message. `auto` means the model can pick between generating a
 * message or calling a function. Specifying a particular function via `{\"type: \"function\",
 * \"function\": {\"name\": \"my_function\"}}` forces the model to call that function. `none` is the
 * default when no functions are present. `auto` is the default if functions are present.
 *
 * @param type The type of the tool. Currently, only `function` is supported.
 * @param function
 */
@Serializable
data class ChatCompletionToolChoiceOption(

  /* The type of the tool. Currently, only `function` is supported. */
  @SerialName(value = "type") val type: ChatCompletionToolChoiceOption.Type? = null,
  @SerialName(value = "function") val function: ChatCompletionNamedToolChoiceFunction? = null
) {

  /**
   * The type of the tool. Currently, only `function` is supported.
   *
   * Values: function
   */
  @Serializable
  enum class Type(val value: kotlin.String) {
    @SerialName(value = "function") function("function")
  }
}
