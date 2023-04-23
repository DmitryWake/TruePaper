package com.ewake.truepaper.features.feed.data

import androidx.paging.PagingSource
import com.ewake.truepaper.core.models.data.NewsBean

abstract class FeedPagingSource: PagingSource<Int, NewsBean>() {
}