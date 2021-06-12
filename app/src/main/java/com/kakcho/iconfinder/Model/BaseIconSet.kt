package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName

class BaseIconSet{
    @SerializedName("iconsets")
    var iconsets: List<IconSet>?= null

    @SerializedName("total_count")
    var total_count: Int?= null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("code")
    var code: Int = 0
}