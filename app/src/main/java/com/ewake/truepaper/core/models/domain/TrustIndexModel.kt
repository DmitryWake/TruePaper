package com.ewake.truepaper.core.models.domain

/**
 * @author Nikolaevskiy Dmitriy
 */
data class TrustIndexModel(
    val totalCount: Long,
    val trust: Long,
    val distrust: Long
)
