package com.ewake.truepaper.core.data.news

import com.ewake.truepaper.core.models.data.NewsBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecommendationNewsSource @Inject constructor(private val newsService: NewsService) :
    NewsSource {
    override suspend fun getNewsFeed(count: Int, offset: Long): List<NewsBean> =
        withContext(Dispatchers.IO) {
            newsService.getRecommendationNewsFeed(count, offset)
        }
}