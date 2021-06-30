package com.shebovich.instasaver.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ImageInfo {
    @SerializedName("width")
    @Expose
    var width: Int? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null
}