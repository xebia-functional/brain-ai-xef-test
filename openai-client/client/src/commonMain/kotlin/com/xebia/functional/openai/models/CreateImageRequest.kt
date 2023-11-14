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
 * @param prompt A text description of the desired image(s). The maximum length is 1000 characters for `dall-e-2` and 4000 characters for `dall-e-3`.
 * @param model 
 * @param n The number of images to generate. Must be between 1 and 10. For `dall-e-3`, only `n=1` is supported.
 * @param quality The quality of the image that will be generated. `hd` creates images with finer details and greater consistency across the image. This param is only supported for `dall-e-3`.
 * @param responseFormat The format in which the generated images are returned. Must be one of `url` or `b64_json`.
 * @param propertySize The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024` for `dall-e-2`. Must be one of `1024x1024`, `1792x1024`, or `1024x1792` for `dall-e-3` models.
 * @param style The style of the generated images. Must be one of `vivid` or `natural`. Vivid causes the model to lean towards generating hyper-real and dramatic images. Natural causes the model to produce more natural, less hyper-real looking images. This param is only supported for `dall-e-3`.
 * @param user A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids). 
 */
@Serializable

data class CreateImageRequest (

    /* A text description of the desired image(s). The maximum length is 1000 characters for `dall-e-2` and 4000 characters for `dall-e-3`. */
    @SerialName(value = "prompt") @Required val prompt: kotlin.String,

    @SerialName(value = "model") val model: com.xebia.functional.openai.models.ext.image.create.CreateImageRequestModel? = null,

    /* The number of images to generate. Must be between 1 and 10. For `dall-e-3`, only `n=1` is supported. */
    @SerialName(value = "n") val n: kotlin.Int? = 1,

    /* The quality of the image that will be generated. `hd` creates images with finer details and greater consistency across the image. This param is only supported for `dall-e-3`. */
    @SerialName(value = "quality") val quality: CreateImageRequest.Quality? = Quality.standard,

    /* The format in which the generated images are returned. Must be one of `url` or `b64_json`. */
    @SerialName(value = "response_format") val responseFormat: CreateImageRequest.ResponseFormat? = ResponseFormat.url,

    /* The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024` for `dall-e-2`. Must be one of `1024x1024`, `1792x1024`, or `1024x1792` for `dall-e-3` models. */
    @SerialName(value = "size") val propertySize: CreateImageRequest.PropertySize? = PropertySize._1024x1024,

    /* The style of the generated images. Must be one of `vivid` or `natural`. Vivid causes the model to lean towards generating hyper-real and dramatic images. Natural causes the model to produce more natural, less hyper-real looking images. This param is only supported for `dall-e-3`. */
    @SerialName(value = "style") val style: CreateImageRequest.Style? = Style.vivid,

    /* A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).  */
    @SerialName(value = "user") val user: kotlin.String? = null

) {

    /**
     * The quality of the image that will be generated. `hd` creates images with finer details and greater consistency across the image. This param is only supported for `dall-e-3`.
     *
     * Values: standard,hd
     */
    @Serializable
    enum class Quality(val value: kotlin.String) {
        @SerialName(value = "standard") standard("standard"),
        @SerialName(value = "hd") hd("hd");
    }
    /**
     * The format in which the generated images are returned. Must be one of `url` or `b64_json`.
     *
     * Values: url,b64Json
     */
    @Serializable
    enum class ResponseFormat(val value: kotlin.String) {
        @SerialName(value = "url") url("url"),
        @SerialName(value = "b64_json") b64Json("b64_json");
    }
    /**
     * The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024` for `dall-e-2`. Must be one of `1024x1024`, `1792x1024`, or `1024x1792` for `dall-e-3` models.
     *
     * Values: _256x256,_512x512,_1024x1024,_1792x1024,_1024x1792
     */
    @Serializable
    enum class PropertySize(val value: kotlin.String) {
        @SerialName(value = "256x256") _256x256("256x256"),
        @SerialName(value = "512x512") _512x512("512x512"),
        @SerialName(value = "1024x1024") _1024x1024("1024x1024"),
        @SerialName(value = "1792x1024") _1792x1024("1792x1024"),
        @SerialName(value = "1024x1792") _1024x1792("1024x1792");
    }
    /**
     * The style of the generated images. Must be one of `vivid` or `natural`. Vivid causes the model to lean towards generating hyper-real and dramatic images. Natural causes the model to produce more natural, less hyper-real looking images. This param is only supported for `dall-e-3`.
     *
     * Values: vivid,natural
     */
    @Serializable
    enum class Style(val value: kotlin.String) {
        @SerialName(value = "vivid") vivid("vivid"),
        @SerialName(value = "natural") natural("natural");
    }
}

