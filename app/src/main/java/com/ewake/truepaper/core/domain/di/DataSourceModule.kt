package com.ewake.truepaper.core.domain.di

import com.ewake.truepaper.core.data.news.NewsSource
import com.ewake.truepaper.core.data.news.NewsSourceImpl
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
    abstract fun bindNewsSource(impl: NewsSourceImpl): NewsSource
}