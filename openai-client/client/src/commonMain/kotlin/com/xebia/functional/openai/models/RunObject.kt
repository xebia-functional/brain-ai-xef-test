/**
 * Please note: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */
@file:Suppress("ArrayInDataClass", "EnumEntryName", "RemoveRedundantQualifierName", "UnusedImport")

package com.xebia.functional.openai.models

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Represents an execution run on a [thread](/docs/api-reference/threads).
 *
 * @param id The identifier, which can be referenced in API endpoints.
 * @param `object` The object type, which is always `thread.run`.
 * @param createdAt The Unix timestamp (in seconds) for when the run was created.
 * @param threadId The ID of the [thread](/docs/api-reference/threads) that was executed on as a
 *   part of this run.
 * @param assistantId The ID of the [assistant](/docs/api-reference/assistants) used for execution
 *   of this run.
 * @param status The status of the run, which can be either `queued`, `in_progress`,
 *   `requires_action`, `cancelling`, `cancelled`, `failed`, `completed`, or `expired`.
 * @param requiredAction
 * @param lastError
 * @param expiresAt The Unix timestamp (in seconds) for when the run will expire.
 * @param startedAt The Unix timestamp (in seconds) for when the run was started.
 * @param cancelledAt The Unix timestamp (in seconds) for when the run was cancelled.
 * @param failedAt The Unix timestamp (in seconds) for when the run failed.
 * @param completedAt The Unix timestamp (in seconds) for when the run was completed.
 * @param model The model that the [assistant](/docs/api-reference/assistants) used for this run.
 * @param instructions The instructions that the [assistant](/docs/api-reference/assistants) used
 *   for this run.
 * @param tools The list of tools that the [assistant](/docs/api-reference/assistants) used for this
 *   run.
 * @param fileIds The list of [File](/docs/api-reference/files) IDs the
 *   [assistant](/docs/api-reference/assistants) used for this run.
 * @param metadata Set of 16 key-value pairs that can be attached to an object. This can be useful
 *   for storing additional information about the object in a structured format. Keys can be a
 *   maximum of 64 characters long and values can be a maxium of 512 characters long.
 */
@Serializable
data class RunObject(

  /* The identifier, which can be referenced in API endpoints. */
  @SerialName(value = "id") @Required val id: kotlin.String,

  /* The object type, which is always `thread.run`. */
  @SerialName(value = "object") @Required val `object`: RunObject.`Object`,

  /* The Unix timestamp (in seconds) for when the run was created. */
  @SerialName(value = "created_at") @Required val createdAt: kotlin.Int,

  /* The ID of the [thread](/docs/api-reference/threads) that was executed on as a part of this run. */
  @SerialName(value = "thread_id") @Required val threadId: kotlin.String,

  /* The ID of the [assistant](/docs/api-reference/assistants) used for execution of this run. */
  @SerialName(value = "assistant_id") @Required val assistantId: kotlin.String,

  /* The status of the run, which can be either `queued`, `in_progress`, `requires_action`, `cancelling`, `cancelled`, `failed`, `completed`, or `expired`. */
  @SerialName(value = "status") @Required val status: RunObject.Status,
  @SerialName(value = "required_action") val requiredAction: RunObjectRequiredAction? = null,
  @SerialName(value = "last_error") @Required val lastError: RunObjectLastError?,

  /* The Unix timestamp (in seconds) for when the run will expire. */
  @SerialName(value = "expires_at") val expiresAt: kotlin.Int? = null,

  /* The Unix timestamp (in seconds) for when the run was started. */
  @SerialName(value = "started_at") @Required val startedAt: kotlin.Int?,

  /* The Unix timestamp (in seconds) for when the run was cancelled. */
  @SerialName(value = "cancelled_at") @Required val cancelledAt: kotlin.Int?,

  /* The Unix timestamp (in seconds) for when the run failed. */
  @SerialName(value = "failed_at") @Required val failedAt: kotlin.Int?,

  /* The Unix timestamp (in seconds) for when the run was completed. */
  @SerialName(value = "completed_at") @Required val completedAt: kotlin.Int?,

  /* The model that the [assistant](/docs/api-reference/assistants) used for this run. */
  @SerialName(value = "model") @Required val model: kotlin.String,

  /* The instructions that the [assistant](/docs/api-reference/assistants) used for this run. */
  @SerialName(value = "instructions") @Required val instructions: kotlin.String,

  /* The list of tools that the [assistant](/docs/api-reference/assistants) used for this run. */
  @SerialName(value = "tools")
  @Required
  val tools:
    kotlin.collections.List<com.xebia.functional.openai.models.ext.assistant.AssistantTools> =
    arrayListOf(),

  /* The list of [File](/docs/api-reference/files) IDs the [assistant](/docs/api-reference/assistants) used for this run. */
  @SerialName(value = "file_ids")
  @Required
  val fileIds: kotlin.collections.List<kotlin.String> = arrayListOf(),

  /* Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long.  */
  @SerialName(value = "metadata") @Required val metadata: kotlinx.serialization.json.JsonObject?
) {

  /**
   * The object type, which is always `thread.run`.
   *
   * Values: thread_run
   */
  @Serializable
  enum class `Object`(val value: kotlin.String) {
    @SerialName(value = "thread.run") thread_run("thread.run")
  }
  /**
   * The status of the run, which can be either `queued`, `in_progress`, `requires_action`,
   * `cancelling`, `cancelled`, `failed`, `completed`, or `expired`.
   *
   * Values: queued,in_progress,requires_action,cancelling,cancelled,failed,completed,expired
   */
  @Serializable
  enum class Status(val value: kotlin.String) {
    @SerialName(value = "queued") queued("queued"),
    @SerialName(value = "in_progress") in_progress("in_progress"),
    @SerialName(value = "requires_action") requires_action("requires_action"),
    @SerialName(value = "cancelling") cancelling("cancelling"),
    @SerialName(value = "cancelled") cancelled("cancelled"),
    @SerialName(value = "failed") failed("failed"),
    @SerialName(value = "completed") completed("completed"),
    @SerialName(value = "expired") expired("expired")
  }
}
