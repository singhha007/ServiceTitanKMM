package com.servicetitan.servicetitankmm.shared.api

import com.servicetitan.servicetitankmm.shared.model.PriceBookResponse
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://api.servicetitan.com/v1/"
private const val ENDPOINT_SERVICE = "services"
const val API_KEY = "45b2c77d-332c-4aec-8595-a2a47b7e9356"

internal class ApiService {

    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json { ignoreUnknownKeys = true })
        }
    }

    @Throws(Exception::class) suspend fun getPricebook(
        page: Int,
        pageSize: Int,
        apiKey: String = API_KEY
    ): PriceBookResponse {
        return httpClient.get(BASE_URL + ENDPOINT_SERVICE) {
            parameter("filter.page", page)
            parameter("filter.pageSize", pageSize)
            parameter("serviceTitanApiKey", apiKey)
        }
    }
}