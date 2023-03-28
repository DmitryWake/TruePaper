package com.ewake.truepaper.core.domain.repository

import com.ewake.truepaper.core.data.user.UserService
import com.ewake.truepaper.core.data.user.UserSharedPref
import com.ewake.truepaper.core.models.domain.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userSharedPref: UserSharedPref,
    private val userApi: UserService
) : UserRepository {

    override suspend fun createUser(): UserModel = withContext(Dispatchers.IO) {
        userApi.createUser().let { UserModel(id = it.id, registerDate = it.registerDate) }
            .also {
                userSharedPref.currentUserModel = it
            }
    }

    override suspend fun getCurrentUser(): UserModel? = withContext(Dispatchers.IO) {
        userSharedPref.currentUserModel
    }
}