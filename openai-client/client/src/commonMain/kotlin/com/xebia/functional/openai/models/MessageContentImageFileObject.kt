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
 * References an image [File](/docs/api-reference/files) in the content of a message.
 *
 * @param type Always `image_file`.
 * @param imageFile
 */
@Serializable
data class MessageContentImageFileObject(

  /* Always `image_file`. */
  @SerialName(value = "type") @Required val type: MessageContentImageFileObject.Type,
  @SerialName(value = "image_file") @Required val imageFile: MessageContentImageFileObjectImageFile
) {

  /**
   * Always `image_file`.
   *
   * Values: image_file
   */
  @Serializable
  enum class Type(val value: kotlin.String) {
    @SerialName(value = "image_file") image_file("image_file")
  }
}
