package com.ewake.truepaper.core.models.data

import com.ewake.truepaper.core.models.domain.NewsModel
import com.ewake.truepaper.core.models.domain.TrustIndexModel

/**
 * @author Nikolaevskiy Dmitriy
 */
data class NewsBean(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val trustIndex: TrustIndexModel,
    val sourceUrl: String
)

fun NewsBean.convertToNewsModel() = NewsModel(
    id, title, description, imageUrl, trustIndex, sourceUrl
)
