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


import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 *
 * @param id 
 * @param `object` 
 * @param deleted 
 */
@Serializable

data class DeleteFileResponse (

    @SerialName(value = "id") @Required val id: kotlin.String,

    @SerialName(value = "object") @Required val `object`: DeleteFileResponse.`Object`,

    @SerialName(value = "deleted") @Required val deleted: kotlin.Boolean

) {

    /**
     * 
     *
     * Values: file
     */
    @Serializable
    enum class `Object`(val value: kotlin.String) {
        @SerialName(value = "file") file("file");
    }
}

