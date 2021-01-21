package com.servicetitan.kmm.shared

import com.servicetitan.kmm.shared.api.ApiService
import com.servicetitan.kmm.shared.model.Show

class ShowManager {

    private val api: ApiService = ApiService()
    private val platform: Platform = Platform()

    fun provideBuild() = platform.build

    fun provideCurrentDate() = platform.currentDate

    @Throws(Exception::class) suspend fun getPopularShows(): List<Show> = api.getPopularShows().results
}
