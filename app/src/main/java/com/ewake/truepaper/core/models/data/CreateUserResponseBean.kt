package com.ewake.truepaper.core.models.data

@kotlinx.serialization.Serializable
data class CreateUserResponseBean(
    val id: String,
    val registerDate: Long
)
