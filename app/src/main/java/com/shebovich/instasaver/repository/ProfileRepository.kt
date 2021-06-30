package com.shebovich.instasaver.repository

import com.shebovich.instasaver.network.InstagramApiService
import com.shebovich.instasaver.db.ProfileDao
import com.shebovich.instasaver.models.ProfileInfo
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ProfileRepository @Inject constructor(val profileDao: ProfileDao,
                                            val instagramApiService: InstagramApiService) {

    fun getProfileInfo(userId: String) : Observable<ProfileInfo> {
        instagramApiService.getProfileInfo()
    }

}