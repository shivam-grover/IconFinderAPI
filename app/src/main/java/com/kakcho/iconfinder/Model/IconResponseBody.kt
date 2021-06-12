package com.kakcho.iconfinder.Model

import com.google.gson.annotations.SerializedName
import java.util.*

class IconResponseBody {
    @SerializedName("icons")
    var icons: ArrayList<Icon>? = null

    @SerializedName("total_count")
    var total_count: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("code")
    var code = 0
}