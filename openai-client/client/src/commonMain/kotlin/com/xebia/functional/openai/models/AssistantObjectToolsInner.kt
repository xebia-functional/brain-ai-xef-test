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

import com.xebia.functional.openai.models.AssistantToolsCode
import com.xebia.functional.openai.models.AssistantToolsFunction
import com.xebia.functional.openai.models.AssistantToolsRetrieval
import com.xebia.functional.openai.models.FunctionObject

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 *
 * @param type The type of tool being defined: `code_interpreter`
 * @param function 
 */
@Serializable

data class AssistantObjectToolsInner (

    /* The type of tool being defined: `code_interpreter` */
    @SerialName(value = "type") @Required val type: AssistantObjectToolsInner.Type,

    @SerialName(value = "function") @Required val function: FunctionObject

) {

    /**
     * The type of tool being defined: `code_interpreter`
     *
     * Values: codeInterpreter,retrieval,function
     */
    @Serializable
    enum class Type(val value: kotlin.String) {
        @SerialName(value = "code_interpreter") codeInterpreter("code_interpreter"),
        @SerialName(value = "retrieval") retrieval("retrieval"),
        @SerialName(value = "function") function("function");
    }
}

