package com.kaushalvasava.apps.instagram.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorModule {
    companion object {
        val httpClient = HttpClient() {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true // used for ignore partially decode a JSON response
                    }
                )
            }
        }
    }
}