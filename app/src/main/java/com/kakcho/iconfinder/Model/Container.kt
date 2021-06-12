package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName

class Container{
    @SerializedName("download_url")
    var download_url: String? = null // https://api.iconfinder.com/v4/icons/182506/containers/ico/download
    @SerializedName("format")
    var format: String?= null // ico
}