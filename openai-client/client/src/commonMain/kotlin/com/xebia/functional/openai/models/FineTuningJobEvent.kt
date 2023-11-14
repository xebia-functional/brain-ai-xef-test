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
 * Fine-tuning job event object
 *
 * @param id 
 * @param createdAt 
 * @param level 
 * @param message 
 * @param `object` 
 */
@Serializable

data class FineTuningJobEvent (

    @SerialName(value = "id") @Required val id: kotlin.String,

    @SerialName(value = "created_at") @Required val createdAt: kotlin.Int,

    @SerialName(value = "level") @Required val level: FineTuningJobEvent.Level,

    @SerialName(value = "message") @Required val message: kotlin.String,

    @SerialName(value = "object") @Required val `object`: FineTuningJobEvent.`Object`

) {

    /**
     * 
     *
     * Values: info,warn,error
     */
    @Serializable
    enum class Level(val value: kotlin.String) {
        @SerialName(value = "info") info("info"),
        @SerialName(value = "warn") warn("warn"),
        @SerialName(value = "error") error("error");
    }
    /**
     * 
     *
     * Values: fineTuningPeriodJobPeriodEvent
     */
    @Serializable
    enum class `Object`(val value: kotlin.String) {
        @SerialName(value = "fine_tuning.job.event") fineTuningPeriodJobPeriodEvent("fine_tuning.job.event");
    }
}

