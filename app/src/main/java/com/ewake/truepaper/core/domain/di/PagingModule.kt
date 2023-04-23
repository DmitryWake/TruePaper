package com.ewake.truepaper.core.domain.di

import com.ewake.truepaper.features.feed.data.FeedPagingSource
import com.ewake.truepaper.features.feed.data.FeedPagingSourceImpl
import com.ewake.truepaper.features.feed.data.RecommendationFeedPagingSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PagingModule {
    @Binds
    @FeedPagingSourceQualifier
    abstract fun bindFeedPagingSource(impl: FeedPagingSourceImpl): FeedPagingSource

    @Binds
    @RecommendationFeedPagingSourceQualifier
    abstract fun bindRecommendationFeedPagingSource(impl: RecommendationFeedPagingSource): FeedPagingSource
}