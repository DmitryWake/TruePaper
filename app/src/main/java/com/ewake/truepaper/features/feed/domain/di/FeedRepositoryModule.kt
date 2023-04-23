package com.ewake.truepaper.features.feed.domain.di

import com.ewake.truepaper.features.feed.domain.repository.FeedRepository
import com.ewake.truepaper.features.feed.domain.repository.FeedRepositoryImpl
import com.ewake.truepaper.features.feed.domain.repository.RecommendationFeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Nikolaevskiy Dmitriy
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class FeedRepositoryModule {
    @Binds
    @FeedRepositoryQualifier
    abstract fun bindFeedRepository(impl: FeedRepositoryImpl): FeedRepository

    @Binds
    @RecommendationFeedRepositoryQualifier
    abstract fun bindRecommendationFeedRepository(impl: RecommendationFeedRepository): FeedRepository
}