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
 * A list of the categories, and whether they are flagged or not.
 *
 * @param hate Content that expresses, incites, or promotes hate based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste. Hateful content aimed at non-protected groups (e.g., chess players) is harrassment.
 * @param hateThreatening Hateful content that also includes violence or serious harm towards the targeted group based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste.
 * @param harassment Content that expresses, incites, or promotes harassing language towards any target.
 * @param harassmentThreatening Harassment content that also includes violence or serious harm towards any target.
 * @param selfHarm Content that promotes, encourages, or depicts acts of self-harm, such as suicide, cutting, and eating disorders.
 * @param selfHarmIntent Content where the speaker expresses that they are engaging or intend to engage in acts of self-harm, such as suicide, cutting, and eating disorders.
 * @param selfHarmInstructions Content that encourages performing acts of self-harm, such as suicide, cutting, and eating disorders, or that gives instructions or advice on how to commit such acts.
 * @param sexual Content meant to arouse sexual excitement, such as the description of sexual activity, or that promotes sexual services (excluding sex education and wellness).
 * @param sexualMinors Sexual content that includes an individual who is under 18 years old.
 * @param violence Content that depicts death, violence, or physical injury.
 * @param violenceGraphic Content that depicts death, violence, or physical injury in graphic detail.
 */
@Serializable

data class CreateModerationResponseResultsInnerCategories (

    /* Content that expresses, incites, or promotes hate based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste. Hateful content aimed at non-protected groups (e.g., chess players) is harrassment. */
    @SerialName(value = "hate") @Required val hate: kotlin.Boolean,

    /* Hateful content that also includes violence or serious harm towards the targeted group based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste. */
    @SerialName(value = "hate/threatening") @Required val hateThreatening: kotlin.Boolean,

    /* Content that expresses, incites, or promotes harassing language towards any target. */
    @SerialName(value = "harassment") @Required val harassment: kotlin.Boolean,

    /* Harassment content that also includes violence or serious harm towards any target. */
    @SerialName(value = "harassment/threatening") @Required val harassmentThreatening: kotlin.Boolean,

    /* Content that promotes, encourages, or depicts acts of self-harm, such as suicide, cutting, and eating disorders. */
    @SerialName(value = "self-harm") @Required val selfHarm: kotlin.Boolean,

    /* Content where the speaker expresses that they are engaging or intend to engage in acts of self-harm, such as suicide, cutting, and eating disorders. */
    @SerialName(value = "self-harm/intent") @Required val selfHarmIntent: kotlin.Boolean,

    /* Content that encourages performing acts of self-harm, such as suicide, cutting, and eating disorders, or that gives instructions or advice on how to commit such acts. */
    @SerialName(value = "self-harm/instructions") @Required val selfHarmInstructions: kotlin.Boolean,

    /* Content meant to arouse sexual excitement, such as the description of sexual activity, or that promotes sexual services (excluding sex education and wellness). */
    @SerialName(value = "sexual") @Required val sexual: kotlin.Boolean,

    /* Sexual content that includes an individual who is under 18 years old. */
    @SerialName(value = "sexual/minors") @Required val sexualMinors: kotlin.Boolean,

    /* Content that depicts death, violence, or physical injury. */
    @SerialName(value = "violence") @Required val violence: kotlin.Boolean,

    /* Content that depicts death, violence, or physical injury in graphic detail. */
    @SerialName(value = "violence/graphic") @Required val violenceGraphic: kotlin.Boolean

)

