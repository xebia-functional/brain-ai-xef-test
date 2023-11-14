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
 * @param model 
 * @param input The text to generate audio for. The maximum length is 4096 characters.
 * @param voice The voice to use when generating the audio. Supported voices are `alloy`, `echo`, `fable`, `onyx`, `nova`, and `shimmer`.
 * @param responseFormat The format to audio in. Supported formats are `mp3`, `opus`, `aac`, and `flac`.
 * @param speed The speed of the generated audio. Select a value from `0.25` to `4.0`. `1.0` is the default.
 */
@Serializable

data class CreateSpeechRequest (

    @SerialName(value = "model") @Required val model: com.xebia.functional.openai.models.ext.speech.create.CreateSpeechRequestModel,

    /* The text to generate audio for. The maximum length is 4096 characters. */
    @SerialName(value = "input") @Required val input: kotlin.String,

    /* The voice to use when generating the audio. Supported voices are `alloy`, `echo`, `fable`, `onyx`, `nova`, and `shimmer`. */
    @SerialName(value = "voice") @Required val voice: CreateSpeechRequest.Voice,

    /* The format to audio in. Supported formats are `mp3`, `opus`, `aac`, and `flac`. */
    @SerialName(value = "response_format") val responseFormat: CreateSpeechRequest.ResponseFormat? = ResponseFormat.mp3,

    /* The speed of the generated audio. Select a value from `0.25` to `4.0`. `1.0` is the default. */
    @SerialName(value = "speed") val speed: kotlin.Double? = (1.0).toDouble()

) {

    /**
     * The voice to use when generating the audio. Supported voices are `alloy`, `echo`, `fable`, `onyx`, `nova`, and `shimmer`.
     *
     * Values: alloy,echo,fable,onyx,nova,shimmer
     */
    @Serializable
    enum class Voice(val value: kotlin.String) {
        @SerialName(value = "alloy") alloy("alloy"),
        @SerialName(value = "echo") echo("echo"),
        @SerialName(value = "fable") fable("fable"),
        @SerialName(value = "onyx") onyx("onyx"),
        @SerialName(value = "nova") nova("nova"),
        @SerialName(value = "shimmer") shimmer("shimmer");
    }
    /**
     * The format to audio in. Supported formats are `mp3`, `opus`, `aac`, and `flac`.
     *
     * Values: mp3,opus,aac,flac
     */
    @Serializable
    enum class ResponseFormat(val value: kotlin.String) {
        @SerialName(value = "mp3") mp3("mp3"),
        @SerialName(value = "opus") opus("opus"),
        @SerialName(value = "aac") aac("aac"),
        @SerialName(value = "flac") flac("flac");
    }
}

