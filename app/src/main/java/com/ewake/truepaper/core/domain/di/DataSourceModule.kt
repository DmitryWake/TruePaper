package com.ewake.truepaper.core.domain.di

import com.ewake.truepaper.core.data.news.NewsSource
import com.ewake.truepaper.core.data.news.NewsSourceImpl
import com.ewake.truepaper.core.data.news.RecommendationNewsSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Nikolaevskiy Dmitriy
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @NewsFeedSource
    abstract fun bindNewsSource(impl: NewsSourceImpl): NewsSource

    @Binds
    @RecommendationFeedSource
    abstract fun bindRecommendationNewsSource(impl: RecommendationNewsSource): NewsSource
}