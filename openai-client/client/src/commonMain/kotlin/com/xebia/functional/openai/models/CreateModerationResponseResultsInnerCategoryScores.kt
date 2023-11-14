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
 * A list of the categories along with their scores as predicted by model.
 *
 * @param hate The score for the category 'hate'.
 * @param hateThreatening The score for the category 'hate/threatening'.
 * @param harassment The score for the category 'harassment'.
 * @param harassmentThreatening The score for the category 'harassment/threatening'.
 * @param selfHarm The score for the category 'self-harm'.
 * @param selfHarmIntent The score for the category 'self-harm/intent'.
 * @param selfHarmInstructions The score for the category 'self-harm/instructions'.
 * @param sexual The score for the category 'sexual'.
 * @param sexualMinors The score for the category 'sexual/minors'.
 * @param violence The score for the category 'violence'.
 * @param violenceGraphic The score for the category 'violence/graphic'.
 */
@Serializable

data class CreateModerationResponseResultsInnerCategoryScores (

    /* The score for the category 'hate'. */
    @SerialName(value = "hate") @Required val hate: kotlin.Double,

    /* The score for the category 'hate/threatening'. */
    @SerialName(value = "hate/threatening") @Required val hateThreatening: kotlin.Double,

    /* The score for the category 'harassment'. */
    @SerialName(value = "harassment") @Required val harassment: kotlin.Double,

    /* The score for the category 'harassment/threatening'. */
    @SerialName(value = "harassment/threatening") @Required val harassmentThreatening: kotlin.Double,

    /* The score for the category 'self-harm'. */
    @SerialName(value = "self-harm") @Required val selfHarm: kotlin.Double,

    /* The score for the category 'self-harm/intent'. */
    @SerialName(value = "self-harm/intent") @Required val selfHarmIntent: kotlin.Double,

    /* The score for the category 'self-harm/instructions'. */
    @SerialName(value = "self-harm/instructions") @Required val selfHarmInstructions: kotlin.Double,

    /* The score for the category 'sexual'. */
    @SerialName(value = "sexual") @Required val sexual: kotlin.Double,

    /* The score for the category 'sexual/minors'. */
    @SerialName(value = "sexual/minors") @Required val sexualMinors: kotlin.Double,

    /* The score for the category 'violence'. */
    @SerialName(value = "violence") @Required val violence: kotlin.Double,

    /* The score for the category 'violence/graphic'. */
    @SerialName(value = "violence/graphic") @Required val violenceGraphic: kotlin.Double

)

