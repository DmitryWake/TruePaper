package com.ewake.truepaper.core.data.user

import com.ewake.truepaper.core.models.data.CreateUserResponseBean
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/user/create")
    suspend fun createUser(): CreateUserResponseBean
}