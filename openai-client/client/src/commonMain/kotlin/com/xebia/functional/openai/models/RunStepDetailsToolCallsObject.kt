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

import com.xebia.functional.openai.models.RunStepDetailsToolCallsObjectToolCallsInner

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Details of the tool call.
 *
 * @param type Always `tool_calls`.
 * @param toolCalls An array of tool calls the run step was involved in. These can be associated with one of three types of tools: `code_interpreter`, `retrieval`, or `function`. 
 */
@Serializable

data class RunStepDetailsToolCallsObject (

    /* Always `tool_calls`. */
    @SerialName(value = "type") @Required val type: RunStepDetailsToolCallsObject.Type,

    /* An array of tool calls the run step was involved in. These can be associated with one of three types of tools: `code_interpreter`, `retrieval`, or `function`.  */
    @SerialName(value = "tool_calls") @Required val toolCalls: kotlin.collections.List<RunStepDetailsToolCallsObjectToolCallsInner>

) {

    /**
     * Always `tool_calls`.
     *
     * Values: toolCalls
     */
    @Serializable
    enum class Type(val value: kotlin.String) {
        @SerialName(value = "tool_calls") toolCalls("tool_calls");
    }
}

