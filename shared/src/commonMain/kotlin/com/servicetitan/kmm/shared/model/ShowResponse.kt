package com.servicetitan.kmm.shared.model

import kotlinx.serialization.*

@Serializable
data class Response<T>(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<T>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)

@Serializable
data class Show(
    @SerialName("backdrop_path") var backdropPath: String? = null,
    @SerialName("first_air_date") var firstAirDate: String? = null,
    @SerialName("genre_ids") var genreIds: List<Int> = emptyList(),
    @SerialName("id") var id: Int = 0,
    @SerialName("name") var name: String = "",
    @SerialName("origin_country") var originCountry: List<String> = emptyList(),
    @SerialName("original_language") var originalLanguage: String? = null,
    @SerialName("original_name") var originalName: String? = null,
    @SerialName("overview") var overview: String? = null,
    @SerialName("popularity") var popularity: Double = 0.0,
    @SerialName("poster_path") var posterPath: String? = null,
    @SerialName("vote_average") var voteAverage: Double = 0.0,
    @SerialName("vote_count") var voteCount: Int = 0,
)