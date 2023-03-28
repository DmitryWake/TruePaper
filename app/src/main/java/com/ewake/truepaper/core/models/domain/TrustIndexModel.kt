package com.ewake.truepaper.core.models.domain

import kotlinx.serialization.Serializable

/**
 * @author Nikolaevskiy Dmitriy
 */
@Serializable
data class TrustIndexModel(
    val totalCount: Int,
    val trustCount: Int,
    val distrustCount: Int
)
