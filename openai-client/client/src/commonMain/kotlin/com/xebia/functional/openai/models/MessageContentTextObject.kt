/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.xebia.functional.openai.models

import com.xebia.functional.openai.models.MessageContentTextObjectText

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * The text content that is part of a message.
 *
 * @param type Always `text`.
 * @param text 
 */
@Serializable

data class MessageContentTextObject (

    /* Always `text`. */
    @SerialName(value = "type") @Required val type: MessageContentTextObject.Type,

    @SerialName(value = "text") @Required val text: MessageContentTextObjectText

) {

    /**
     * Always `text`.
     *
     * Values: text
     */
    @Serializable
    enum class Type(val value: kotlin.String) {
        @SerialName(value = "text") text("text");
    }
}

