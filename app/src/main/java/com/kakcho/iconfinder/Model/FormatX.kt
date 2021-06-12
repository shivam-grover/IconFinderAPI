package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName

class FormatX{
    @SerializedName("download_url")
    var download_url: String? = null // https://api.iconfinder.com/v4/icons/182502/formats/svg/516500/download
    @SerializedName("format")
    var format: String? = null // svg
}