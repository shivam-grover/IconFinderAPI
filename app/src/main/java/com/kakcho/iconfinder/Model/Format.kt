package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName

class Format {
    @SerializedName("download_url")
    var download_url: String? = null // https://api.iconfinder.com/v4/icons/182502/formats/png/16/download

    @SerializedName("format")
    var format: String? = null // png

    @SerializedName("preview_url")
    var preview_url: String? = null // https://cdn2.iconfinder.com/data/icons/iconfinder-logo/409/text_invert-16.png
}