package com.shebovich.instasaver.api

import com.shebovich.instasaver.models.UserSession
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface InstagramApiService {

    @FormUrlEncoded
    @POST("/oauth/access_token")
    fun authUser(@Field("client_id") clientId: String,
                 @Field("grant_type") grantType: String,
                 @Field("client_secret") clientSecret: String,
                 @Field("redirect_uri") redirectUri: String,
                 @Field("code") code: String) : Observable<UserSession>
}