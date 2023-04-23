package com.ewake.truepaper.core.domain.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsFeedSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RecommendationFeedSource
