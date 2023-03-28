package com.ewake.truepaper.core.models.data

import kotlinx.serialization.Serializable

@Serializable
data class NewsFeedRequestBean(
    val count: Int,
    val offset: Long
)
