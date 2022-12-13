package com.ewake.truepaper.core.models.domain

/**
 * @author Nikolaevskiy Dmitriy
 */
data class NewsModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val trustIndex: TrustIndexModel,
    val sourceUrl: String
)
