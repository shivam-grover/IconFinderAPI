package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class BaseIcons{
    @SerializedName("icons")
    var icons: ArrayList<Icon>? = null

    @SerializedName("total_count")
    var total_count: Int? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("code")
    var code: Int = 0
}