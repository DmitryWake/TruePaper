package com.ewake.truepaper.core.data.interceptors

import com.ewake.truepaper.core.data.user.UserSharedPref
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor(
    private val userSharedPref: UserSharedPref
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().header(TOKEN_HEADER_NAME, "Bearer abc123")
        userSharedPref.currentUserId?.let { request.header(USER_ID_HEADER_NAME, it) }

        return chain.proceed(request.build())
    }

    companion object {
        private const val TOKEN_HEADER_NAME = "Authorization"
        private const val USER_ID_HEADER_NAME = "UserId"
    }
}