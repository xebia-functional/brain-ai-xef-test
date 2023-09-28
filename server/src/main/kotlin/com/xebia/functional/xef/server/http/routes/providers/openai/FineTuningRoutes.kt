package com.xebia.functional.xef.server.http.routes.providers.openai

import com.xebia.functional.xef.server.http.routes.providers.handleForwardToProvider
import io.ktor.client.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.serialization.json.JsonObject

/**
 * https://platform.openai.com/docs/api-reference/fine-tuning
 */
fun Route.oaiFineTuning(
    client: HttpClient,
) = route("v1/fine_tuning") {
    get("jobs") {
        // has query parameters btw
        val response = handleForwardToProvider(client)
    }

    post("jobs") {
        val bodyJson = call.receive<JsonObject>()

        val response = handleForwardToProvider(client)
    }

    get("jobs/{id}") {
        val id = call.parameters.getOrFail("id")

        val response = handleForwardToProvider(client)
    }

    post("jobs/{id}/cancel") {
        val id = call.parameters.getOrFail("id")

        val response = handleForwardToProvider(client)
    }

    get("jobs/{id}/events") {
        // has query parameters btw
        val id = call.parameters.getOrFail("id")

        val response = handleForwardToProvider(client)
    }
}
