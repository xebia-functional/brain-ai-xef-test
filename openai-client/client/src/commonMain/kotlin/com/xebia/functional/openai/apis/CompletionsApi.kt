/**
 * Please note: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */
@file:Suppress("ArrayInDataClass", "EnumEntryName", "RemoveRedundantQualifierName", "UnusedImport")

package com.xebia.functional.openai.apis

import com.xebia.functional.openai.infrastructure.*
import com.xebia.functional.openai.models.CreateCompletionRequest
import com.xebia.functional.openai.models.CreateCompletionResponse
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.Json

open class CompletionsApi : ApiClient {

  constructor(
    baseUrl: String = ApiClient.BASE_URL,
    httpClientEngine: HttpClientEngine? = null,
    httpClientConfig: ((HttpClientConfig<*>) -> Unit)? = null,
    jsonSerializer: Json = ApiClient.JSON_DEFAULT
  ) : super(
    baseUrl = baseUrl,
    httpClientEngine = httpClientEngine,
    httpClientConfig = httpClientConfig,
    jsonBlock = jsonSerializer
  )

  constructor(
    baseUrl: String,
    httpClient: HttpClient
  ) : super(baseUrl = baseUrl, httpClient = httpClient)

  /**
   * Creates a completion for the provided prompt and parameters.
   *
   * @param createCompletionRequest
   * @return CreateCompletionResponse
   */
  @Suppress("UNCHECKED_CAST")
  open suspend fun createCompletion(
    createCompletionRequest: CreateCompletionRequest
  ): HttpResponse<CreateCompletionResponse> {

    val localVariableAuthNames = listOf<String>("ApiKeyAuth")

    val localVariableBody = createCompletionRequest

    val localVariableQuery = mutableMapOf<String, List<String>>()
    val localVariableHeaders = mutableMapOf<String, String>()

    val localVariableConfig =
      RequestConfig<kotlin.Any?>(
        RequestMethod.POST,
        "/completions",
        query = localVariableQuery,
        headers = localVariableHeaders,
        requiresAuthentication = true,
      )

    return jsonRequest(localVariableConfig, localVariableBody, localVariableAuthNames).wrap()
  }
}
