package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName

class VectorSize{
    @SerializedName("formats")
    var formats: List<FormatX>?= null
    @SerializedName("size")
    var size: String? = null // 409
    @SerializedName("size_height")
    var size_height: String? = null // 68
    @SerializedName("size_width")
    var size_width: String? = null // 409
    @SerializedName("target_sizes")
    var target_sizes: List<List<Int>>?= null
}