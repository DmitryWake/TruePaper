package com.ewake.truepaper.core.data.demo

import com.ewake.truepaper.core.data.NewsSource
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
                imageUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fklike.net%2F844-nyashnye-kotiki-milye-kartinki-30-foto.html&psig=AOvVaw0Mop8BNi6zXL6omBAq8Afu&ust=1671013975385000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCNDcxqWy9vsCFQAAAAAdAAAAABAE",
                trustIndex = TrustIndexModel(5, 3, 2),
                sourceUrl = "no"
            )
        }
    }
}