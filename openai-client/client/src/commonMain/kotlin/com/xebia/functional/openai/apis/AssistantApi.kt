/**
 * Please note: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */
@file:Suppress("ArrayInDataClass", "EnumEntryName", "RemoveRedundantQualifierName", "UnusedImport")

package com.xebia.functional.openai.apis

import com.xebia.functional.openai.infrastructure.*
import com.xebia.functional.openai.models.AssistantObject
import com.xebia.functional.openai.models.ModifyAssistantRequest
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.Json

open class AssistantApi : ApiClient {

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
   * Modifies an assistant.
   *
   * @param assistantId The ID of the assistant to modify.
   * @param modifyAssistantRequest
   * @return AssistantObject
   */
  @Suppress("UNCHECKED_CAST")
  open suspend fun modifyAssistant(
    assistantId: kotlin.String,
    modifyAssistantRequest: ModifyAssistantRequest
  ): HttpResponse<AssistantObject> {

    val localVariableAuthNames = listOf<String>("ApiKeyAuth")

    val localVariableBody = modifyAssistantRequest

    val localVariableQuery = mutableMapOf<String, List<String>>()
    val localVariableHeaders = mutableMapOf<String, String>()

    val localVariableConfig =
      RequestConfig<kotlin.Any?>(
        RequestMethod.POST,
        "/assistants/{assistant_id}".replace("{" + "assistant_id" + "}", "$assistantId"),
        query = localVariableQuery,
        headers = localVariableHeaders,
        requiresAuthentication = true,
      )

    return jsonRequest(localVariableConfig, localVariableBody, localVariableAuthNames).wrap()
  }
}
