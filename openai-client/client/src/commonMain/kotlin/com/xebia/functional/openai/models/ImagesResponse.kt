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

import com.xebia.functional.openai.models.Image

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 *
 * @param created 
 * @param `data` 
 */
@Serializable

data class ImagesResponse (

    @SerialName(value = "created") @Required val created: kotlin.Int,

    @SerialName(value = "data") @Required val `data`: kotlin.collections.List<Image>

)

