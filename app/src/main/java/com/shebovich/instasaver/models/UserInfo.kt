package com.shebovich.instasaver.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class UserInfo {
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("full_name")
    @Expose
    var fullName: String? = null

    @SerializedName("follower_count")
    @Expose
    var followerCount: Int? = null

    @SerializedName("following_count")
    @Expose
    var followingCount: Int? = null

    @SerializedName("biography")
    @Expose
    var biography: String? = null

    @SerializedName("external_url")
    @Expose
    var externalUrl: String? = null

    @SerializedName("profile_pic_url_small")
    @Expose
    var profilePicUrlSmall: String? = null

    @SerializedName("hd_profile_pic_url_info")
    @Expose
    var hdProfilePicUrlInfo: ImageInfo? = null
}