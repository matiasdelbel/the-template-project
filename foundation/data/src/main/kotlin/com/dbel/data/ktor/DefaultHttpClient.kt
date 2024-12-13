package com.dbel.data.ktor

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun DefaultHttpClient(
    interceptor: (HttpRequestBuilder) -> Unit = {},
    block: HttpClientConfig<*>.() -> Unit = {}
): HttpClient {
    val httpClient = HttpClient {
        install(plugin = ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }

        block()
    }

    httpClient.plugin(HttpSend).intercept { request ->
        interceptor(request)

        execute(request)
    }

    return httpClient
}
