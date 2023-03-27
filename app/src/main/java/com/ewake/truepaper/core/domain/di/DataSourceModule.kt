package com.ewake.truepaper.core.domain.di

import com.ewake.truepaper.BuildConfig
import com.ewake.truepaper.core.data.news.NewsSource
import com.ewake.truepaper.core.data.news.demo.NewsSourceDemo
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * @author Nikolaevskiy Dmitriy
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindNewsSource(impl: NewsSourceDemo): NewsSource
}