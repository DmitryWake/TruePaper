package com.ewake.truepaper.core.data.news

import com.ewake.truepaper.core.models.data.NewsBean

/**
 * @author Nikolaevskiy Dmitriy
 */
interface NewsSource {
    suspend fun getNewsFeed(page: Int): List<NewsBean>
}