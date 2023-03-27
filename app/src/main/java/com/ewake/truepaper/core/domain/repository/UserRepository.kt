package com.ewake.truepaper.core.domain.repository

import com.ewake.truepaper.core.models.domain.UserModel

interface UserRepository {
    suspend fun createUser(): UserModel
    suspend fun getCurrentUser(): UserModel?
}