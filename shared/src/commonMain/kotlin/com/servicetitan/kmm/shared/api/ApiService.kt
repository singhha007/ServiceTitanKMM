package com.servicetitan.kmm.shared.api

import com.servicetitan.kmm.shared.model.Response
import com.servicetitan.kmm.shared.model.Show
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY_TAG = "api_key"
private const val ENDPOINT_POPULAR = "tv/popular"
private const val API_KEY = "5812e4b63553d1273a420416fddeed72"

internal class ApiService {

    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json { ignoreUnknownKeys = true })
        }
    }

    @Throws(Exception::class) suspend fun getPopularShows(): Response<Show> =
        httpClient.get(BASE_URL + ENDPOINT_POPULAR) { parameter(API_KEY_TAG, API_KEY) }
}