package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName

class RasterSize{
    @SerializedName("formats")
    var formats: List<Format>?= null
    @SerializedName("size")
    var size: String? = null // 16
    @SerializedName("size_height")
    var license_id: String? = null // 2
    @SerializedName("size_width")
    var size_width: String? = null // 16
}