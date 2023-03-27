package com.ewake.truepaper.features.feed.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ewake.truepaper.core.data.news.NewsSource
import com.ewake.truepaper.core.models.data.NewsBean
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
class FeedPagingSource @Inject constructor(private val dataSource: NewsSource) :
    PagingSource<Int, NewsBean>() {
    override fun getRefreshKey(state: PagingState<Int, NewsBean>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsBean> {
        val nextPage = params.key ?: 1

        val list = dataSource.getNewsFeed(nextPage)

        return LoadResult.Page(
            data = list,
            prevKey = if (nextPage == 1) null else nextPage - 1,
            nextKey = if (list.isEmpty()) null else nextPage + 1
        )
    }
}