package com.ewake.truepaper.core.data.news

import com.ewake.truepaper.core.models.data.NewsBean
import com.ewake.truepaper.core.models.data.SetNewsScoreRequestBean
import com.ewake.truepaper.core.models.domain.TrustIndexModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NewsService {
    @GET("/newsFeed")
    suspend fun getNewsFeed(
        @Query("count") count: Int,
        @Query("offset") offset: Long
    ): List<NewsBean>

    @GET("/newsFeed/recommendation")
    suspend fun getRecommendationNewsFeed(
        @Query("count") count: Int,
        @Query("offset") offset: Long
    ): List<NewsBean>

    @POST("/newsUserScore/setScore")
    suspend fun setNewsScore(@Body request: SetNewsScoreRequestBean): TrustIndexModel
}