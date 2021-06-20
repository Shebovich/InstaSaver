package com.shebovich.instasaver.repository

import com.shebovich.instasaver.api.InstagramApiService
import com.shebovich.instasaver.db.SessionManager
import com.shebovich.instasaver.models.UserSession
import com.shebovich.instasaver.other.Constants
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class LoginRepository @Inject constructor(private val sessionManager: SessionManager,
                                          private val instagramApiService: InstagramApiService) {

    fun authUser() : Observable<UserSession> {
      return  instagramApiService.authUser(Constants.CLIENT_ID,
            Constants.GRANT_TYPE,
            Constants.CLIENT_SECRET,
            Constants.REDIRECT_URL,
            sessionManager.getCode()!!)
    }
}