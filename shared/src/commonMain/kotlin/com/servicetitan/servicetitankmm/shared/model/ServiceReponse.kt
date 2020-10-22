package com.servicetitan.servicetitankmm.shared.model

import kotlinx.serialization.*

@Serializable
data class PriceBookResponse(
    @SerialName("page") var page: Int = 1,
    @SerialName("pageSize") var pageSize: Int = 10,
    @SerialName("totalCount") var totalCount: Int = 0,
    @SerialName("hasMore") var hasMore: Boolean = false,
    @SerialName("data") var data: List<PricebookItem> = emptyList()
)

@Serializable
data class PricebookItem(
    @SerialName("id") var id: Int = 0,
    @SerialName("name") var name: String? = "",
    @SerialName("price") var price: Double = 0.0
) {
    val thumbnailUrl: String
        get() = "https://picsum.photos/id/${id % 1000}/75/75"
}