package com.ewake.truepaper.core.data.user

import android.content.Context
import com.ewake.truepaper.core.models.domain.UserModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserSharedPref @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPref =
        context.getSharedPreferences(USER_SHARED_PREF_NAME, Context.MODE_PRIVATE)

    var currentUserId: String?
        get() = sharedPref.getString(USER_ID_KEY, null)
        set(value) {
            with(sharedPref.edit()) {
                putString(USER_ID_KEY, value)
                apply()
            }
        }

    var currentUserRegisterDate: Long
        get() = sharedPref.getLong(USER_REGISTER_DATE_KEY, 0)
        set(value) {
            with(sharedPref.edit()) {
                putLong(USER_REGISTER_DATE_KEY, value)
                apply()
            }
        }

    var currentUserModel: UserModel?
        set(value) {
            value?.let {
                currentUserId = it.id
                currentUserRegisterDate = it.registerDate
            }
        }
        get() = currentUserId?.let { UserModel(it, currentUserRegisterDate) }

    companion object {
        private const val USER_SHARED_PREF_NAME = "userSharedPref"
        private const val USER_ID_KEY = "userId"
        private const val USER_REGISTER_DATE_KEY = "registerDate"
    }
}