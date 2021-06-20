package com.shebovich.instasaver.db

import android.content.SharedPreferences
import com.shebovich.instasaver.other.Constants
import javax.inject.Inject

class SessionManager @Inject constructor(private val preferences: SharedPreferences) {

    fun getCode() = preferences.getString(Constants.INST_TOKEN, Constants.INST_TOKEN)

    fun setCode(token: String) {
        val editor = preferences.edit()
        editor.putString(Constants.INST_TOKEN, token)
        editor.apply()
    }

    fun isShowHelp() = preferences.getBoolean(Constants.SHOW_DIALOG, true)

    fun setShowDialog(isShow: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(Constants.SHOW_DIALOG, isShow)
        editor.apply()
    }

    fun setUserId(userId: String) {
        val editor = preferences.edit()
        editor.putString(Constants.USER_ID, userId)
        editor.apply()
    }

    fun setAccesToken(accessToken: String) {
        val editor = preferences.edit()
        editor.putString(Constants.ACCESS_TOKEN, accessToken)
        editor.apply()
    }
    fun getUserId() = preferences.getString(Constants.USER_ID, Constants.USER_ID)
    fun getAccessToken() = preferences.getString(Constants.ACCESS_TOKEN, Constants.ACCESS_TOKEN)
}