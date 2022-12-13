package com.ewake.truepaper.features.feed.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ewake.truepaper.core.models.data.convertToNewsModel
import com.ewake.truepaper.core.models.domain.NewsModel
import com.ewake.truepaper.features.feed.data.FeedPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
class FeedRepositoryImpl @Inject constructor(
    private val pagingSource: FeedPagingSource
) : FeedRepository {
    override fun getPagingFeedFlow(pageSize: Int): Flow<PagingData<NewsModel>> =
        Pager(PagingConfig(pageSize = pageSize)) {
            pagingSource
        }.flow.map { data ->
            data.map {
                it.convertToNewsModel()
            }
        }
}