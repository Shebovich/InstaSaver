package com.shebovich.instasaver.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ProfileInfo {
    @SerializedName("user")
    @Expose
    var userInfo: UserInfo? = null

    @SerializedName("hd_profile_pic_versions")
    @Expose
    var hdProfilePicVersions: List<ImageInfo>? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}