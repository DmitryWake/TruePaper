package com.ewake.truepaper.core.models.data

import kotlinx.serialization.Serializable

@Serializable
data class SetNewsScoreRequestBean(
    val newsId: Int,
    val score: Boolean,
    val isFromRecommendation: Boolean
)
