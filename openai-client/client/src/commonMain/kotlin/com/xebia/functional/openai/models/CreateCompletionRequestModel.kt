/**
 * Please note: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */
@file:Suppress("ArrayInDataClass", "EnumEntryName", "RemoveRedundantQualifierName", "UnusedImport")

package com.xebia.functional.openai.models

import kotlinx.serialization.*

/**
 * ID of the model to use. You can use the [List models](/docs/api-reference/models/list) API to see
 * all of your available models, or see our [Model overview](/docs/models/overview) for descriptions
 * of them.
 *
 * Values:
 * babbage_002,davinci_002,gpt_3_5_turbo_instruct,text_davinci_003,text_davinci_002,text_davinci_001,code_davinci_002,text_curie_001,text_babbage_001,text_ada_001
 */
@Serializable
enum class CreateCompletionRequestModel(val value: kotlin.String) {

  @SerialName(value = "babbage-002") babbage_002("babbage-002"),
  @SerialName(value = "davinci-002") davinci_002("davinci-002"),
  @SerialName(value = "gpt-3.5-turbo-instruct") gpt_3_5_turbo_instruct("gpt-3.5-turbo-instruct"),
  @SerialName(value = "text-davinci-003") text_davinci_003("text-davinci-003"),
  @SerialName(value = "text-davinci-002") text_davinci_002("text-davinci-002"),
  @SerialName(value = "text-davinci-001") text_davinci_001("text-davinci-001"),
  @SerialName(value = "code-davinci-002") code_davinci_002("code-davinci-002"),
  @SerialName(value = "text-curie-001") text_curie_001("text-curie-001"),
  @SerialName(value = "text-babbage-001") text_babbage_001("text-babbage-001"),
  @SerialName(value = "text-ada-001") text_ada_001("text-ada-001");

  /**
   * Override [toString()] to avoid using the enum variable name as the value, and instead use the
   * actual value defined in the API spec file.
   *
   * This solves a problem when the variable name and its value are different, and ensures that the
   * client sends the correct enum values to the server always.
   */
  override fun toString(): kotlin.String = value

  companion object {
    /** Converts the provided [data] to a [String] on success, null otherwise. */
    fun encode(data: kotlin.Any?): kotlin.String? =
      if (data is CreateCompletionRequestModel) "$data" else null

    /** Returns a valid [CreateCompletionRequestModel] for [data], null otherwise. */
    fun decode(data: kotlin.Any?): CreateCompletionRequestModel? =
      data?.let {
        val normalizedData = "$it".lowercase()
        values().firstOrNull { value -> it == value || normalizedData == "$value".lowercase() }
      }
  }
}
