package com.ewake.truepaper.core.data.news.demo

import com.ewake.truepaper.core.data.news.NewsSource
import com.ewake.truepaper.core.models.data.NewsBean
import com.ewake.truepaper.core.models.domain.TrustIndexModel
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
class NewsSourceDemo @Inject constructor() : NewsSource {
    override suspend fun getNewsFeed(page: Int): List<NewsBean> {
        return List(5) {
            val counter = it + 5 * (page - 1)
            NewsBean(
                id = counter.toString(),
                title = "Заголовок $counter",
                description = "Описание $counter. Описание $counter. Описание $counter. Описание $counter. Описание $counter.",
                imageUrl = "https://klike.net/uploads/posts/2018-11/1542784826_1.jpg",
                trustIndex = TrustIndexModel(5, 3, 2),
                sourceUrl = "no"
            )
        }
    }
}