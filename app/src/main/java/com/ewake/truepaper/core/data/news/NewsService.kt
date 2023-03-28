package com.ewake.truepaper.core.data.news

import com.ewake.truepaper.core.models.data.NewsBean
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/newsFeed")
    suspend fun getNewsFeed(@Query("count") count: Int, @Query("offset") offset: Long): List<NewsBean>
}