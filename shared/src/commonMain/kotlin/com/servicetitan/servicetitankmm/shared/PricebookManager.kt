package com.servicetitan.servicetitankmm.shared

import com.servicetitan.servicetitankmm.shared.model.PriceBookResponse
import com.servicetitan.servicetitankmm.shared.api.ApiService

class PricebookManager {

    private val api: ApiService = ApiService()

    fun providePlatform() = Platform().platform

    @Throws(Exception::class) suspend fun getPricebook(page: Int, pageSize: Int): PriceBookResponse {
        return api.getPricebook(page, pageSize)
    }
}
