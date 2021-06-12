package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName

class License{
    @SerializedName("license_id")
    var license_id: String? = null // 71
    @SerializedName("name")
    var name: String? = null // Basic license
    @SerializedName("scope")
    var scope: String? = null// free
    @SerializedName("url")
    var url: String? = null // https://www.iconfinder.com/licenses/basic
}