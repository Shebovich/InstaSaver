package com.shebovich.instasaver.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class VideoInfo {
    @SerializedName("shortcode")
    @Expose
    var shortcode: String? = null

    @SerializedName("video_url")
    @Expose
    var videoUrl: String? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null
}