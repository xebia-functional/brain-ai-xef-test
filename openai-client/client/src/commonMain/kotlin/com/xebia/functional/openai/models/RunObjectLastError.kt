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
 * The last error associated with this run. Will be `null` if there are no errors.
 *
 * @param code One of `server_error` or `rate_limit_exceeded`.
 * @param message A human-readable description of the error.
 */
@Serializable

data class RunObjectLastError (

    /* One of `server_error` or `rate_limit_exceeded`. */
    @SerialName(value = "code") @Required val code: RunObjectLastError.Code,

    /* A human-readable description of the error. */
    @SerialName(value = "message") @Required val message: kotlin.String

) {

    /**
     * One of `server_error` or `rate_limit_exceeded`.
     *
     * Values: serverError,rateLimitExceeded
     */
    @Serializable
    enum class Code(val value: kotlin.String) {
        @SerialName(value = "server_error") serverError("server_error"),
        @SerialName(value = "rate_limit_exceeded") rateLimitExceeded("rate_limit_exceeded");
    }
}

