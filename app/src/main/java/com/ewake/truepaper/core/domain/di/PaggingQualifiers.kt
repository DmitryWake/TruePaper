package com.ewake.truepaper.core.domain.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FeedPagingSourceQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RecommendationFeedPagingSourceQualifier