package com.ewake.truepaper.features.feed.data

import androidx.paging.PagingState
import com.ewake.truepaper.core.data.news.NewsSource
import com.ewake.truepaper.core.domain.di.RecommendationFeedSource
import com.ewake.truepaper.core.models.data.NewsBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecommendationFeedPagingSource @Inject constructor(
    @RecommendationFeedSource
    private val dataSource: NewsSource
) : FeedPagingSource() {
    override fun getRefreshKey(state: PagingState<Int, NewsBean>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsBean> =
        withContext(Dispatchers.IO) {
            val nextPage = params.key ?: 1
            val offset = params.loadSize * (nextPage - 1).toLong()

            runCatching {
                val list = dataSource.getNewsFeed(params.loadSize, offset)

                LoadResult.Page(
                    data = list,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (list.isEmpty()) null else nextPage + 1
                )
            }.getOrElse { LoadResult.Error(it) }
        }
}