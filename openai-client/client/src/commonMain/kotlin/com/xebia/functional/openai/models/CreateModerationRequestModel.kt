/**
 * Please note: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */
@file:Suppress("ArrayInDataClass", "EnumEntryName", "RemoveRedundantQualifierName", "UnusedImport")

package com.xebia.functional.openai.models

import kotlinx.serialization.*

/**
 * Two content moderations models are available: `text-moderation-stable` and
 * `text-moderation-latest`. The default is `text-moderation-latest` which will be automatically
 * upgraded over time. This ensures you are always using our most accurate model. If you use
 * `text-moderation-stable`, we will provide advanced notice before updating the model. Accuracy of
 * `text-moderation-stable` may be slightly lower than for `text-moderation-latest`.
 *
 * Values: text_moderation_latest,text_moderation_stable
 */
@Serializable
enum class CreateModerationRequestModel(val value: kotlin.String) {

  @SerialName(value = "text-moderation-latest") text_moderation_latest("text-moderation-latest"),
  @SerialName(value = "text-moderation-stable") text_moderation_stable("text-moderation-stable");

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
      if (data is CreateModerationRequestModel) "$data" else null

    /** Returns a valid [CreateModerationRequestModel] for [data], null otherwise. */
    fun decode(data: kotlin.Any?): CreateModerationRequestModel? =
      data?.let {
        val normalizedData = "$it".lowercase()
        values().firstOrNull { value -> it == value || normalizedData == "$value".lowercase() }
      }
  }
}
