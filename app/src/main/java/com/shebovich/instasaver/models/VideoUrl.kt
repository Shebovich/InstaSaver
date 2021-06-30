package com.shebovich.instasaver.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class VideoUrl {
    @SerializedName("no_of_videos")
    @Expose
    var noOfVideos: Int? = null
    @SerializedName("info")
    @Expose
    var info: List<VideoInfo>? = null
}