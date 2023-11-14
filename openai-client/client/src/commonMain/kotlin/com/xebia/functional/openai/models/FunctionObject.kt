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
 * @param name The name of the function to be called. Must be a-z, A-Z, 0-9, or contain underscores and dashes, with a maximum length of 64.
 * @param parameters 
 * @param description A description of what the function does, used by the model to choose when and how to call the function.
 */
@Serializable

data class FunctionObject (

    /* The name of the function to be called. Must be a-z, A-Z, 0-9, or contain underscores and dashes, with a maximum length of 64. */
    @SerialName(value = "name") @Required val name: kotlin.String,

    @SerialName(value = "parameters") @Required val parameters: kotlinx.serialization.json.JsonObject,

    /* A description of what the function does, used by the model to choose when and how to call the function. */
    @SerialName(value = "description") val description: kotlin.String? = null

)

