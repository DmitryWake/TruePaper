package com.ewake.truepaper.core.models.domain

/**
 * @author Nikolaevskiy Dmitriy
 */
data class NewsModel(
    val id: Int,
    val newsBody: String,
    val imageUrl: String? = null,
    val sourceUrl: String,
    val date: Long,
    val trustIndex: TrustIndexModel,
    val currentUserScore: Boolean? = null
)
