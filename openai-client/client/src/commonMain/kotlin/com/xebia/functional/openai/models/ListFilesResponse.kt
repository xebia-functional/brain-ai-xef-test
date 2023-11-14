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

import com.xebia.functional.openai.models.OpenAIFile

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 *
 * @param `data` 
 * @param `object` 
 */
@Serializable

data class ListFilesResponse (

    @SerialName(value = "data") @Required val `data`: kotlin.collections.List<OpenAIFile>,

    @SerialName(value = "object") @Required val `object`: ListFilesResponse.`Object`

) {

    /**
     * 
     *
     * Values: list
     */
    @Serializable
    enum class `Object`(val value: kotlin.String) {
        @SerialName(value = "list") list("list");
    }
}

