package com.shebovich.instasaver.network

import com.shebovich.instasaver.models.ProfileInfo
import com.shebovich.instasaver.models.UserSession
import com.shebovich.instasaver.models.VideoInfo
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface InstagramApiService {

    @FormUrlEncoded
    @POST("/oauth/access_token")
    fun authUser(@Field("client_id") clientId: String,
                 @Field("grant_type") grantType: String,
                 @Field("client_secret") clientSecret: String,
                 @Field("redirect_uri") redirectUri: String,
                 @Field("code") code: String) : Observable<UserSession>

    @Streaming
    @GET
    fun downloadFile(@Url fileUrl: String): Observable<Response<ResponseBody>>

    @POST("/profile")
    fun getProfileInfo(@Query("userid") userId: String) : Observable<ProfileInfo>

    @POST("/video")
    fun getVideoInfo(@Query("link") videoLink: String) : Observable<VideoInfo>
}