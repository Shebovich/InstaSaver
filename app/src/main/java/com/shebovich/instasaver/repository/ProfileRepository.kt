package com.shebovich.instasaver.repository

import com.shebovich.instasaver.api.InstagramApiService
import com.shebovich.instasaver.db.ProfileDao
import javax.inject.Inject

class ProfileRepository @Inject constructor(profileDao: ProfileDao,
                                            instagramApiService: InstagramApiService) {

}