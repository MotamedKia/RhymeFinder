package com.example.rhymefinder.logics

import com.example.rhymefinder.models.RhymeFind
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

suspend fun getRhyme(query: String): RhymeFind {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json)
        }
    }
    val rhymeFound: RhymeFind =
        client.get("https://rhyming.ir/api/rhyme-finder?api=616d69722e6d6f74616d65646b696140676d61696c2e636f6d&w=${query}&sb=1&mfe=2&eq=1")
            .body()

    client.close()

    return rhymeFound
}