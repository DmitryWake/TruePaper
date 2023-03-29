package com.ewake.truepaper.features.feed.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ewake.truepaper.core.data.news.NewsService
import com.ewake.truepaper.core.models.data.SetNewsScoreRequestBean
import com.ewake.truepaper.core.models.data.convertToNewsModel
import com.ewake.truepaper.core.models.domain.NewsModel
import com.ewake.truepaper.core.models.domain.TrustIndexModel
import com.ewake.truepaper.features.feed.data.FeedPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
class FeedRepositoryImpl @Inject constructor(
    private val pagingSource: FeedPagingSource,
    private val newsService: NewsService
) : FeedRepository {
    override fun getPagingFeedFlow(count: Int): Flow<PagingData<NewsModel>> =
        Pager(PagingConfig(pageSize = count)) {
            pagingSource
        }.flow.map { data ->
            data.map {
                it.convertToNewsModel()
            }
        }

    override suspend fun setNewsScore(newsId: Int, score: Boolean): TrustIndexModel =
        withContext(Dispatchers.IO) {
            newsService.setNewsScore(SetNewsScoreRequestBean(newsId, score))
        }
}