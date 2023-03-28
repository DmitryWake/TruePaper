package com.ewake.truepaper.core.models.data

import com.ewake.truepaper.core.models.domain.NewsModel
import com.ewake.truepaper.core.models.domain.TrustIndexModel
import kotlinx.serialization.Serializable

/**
 * @author Nikolaevskiy Dmitriy
 */
@Serializable
data class NewsBean(
    val id: Int,
    val newsBody: String,
    val imageUrl: String? = null,
    val sourceUrl: String,
    val date: Long,
    val trustIndex: TrustIndexModel,
    val currentUserScore: Boolean? = null
)

fun NewsBean.convertToNewsModel() = NewsModel(
    id, newsBody, imageUrl, sourceUrl, date, trustIndex, currentUserScore
)
