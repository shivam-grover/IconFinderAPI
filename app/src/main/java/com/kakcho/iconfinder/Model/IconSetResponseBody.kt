package com.kakcho.iconfinder.Model
import com.google.gson.annotations.SerializedName
import java.util.*

class IconSetResponseBody {
    @SerializedName("iconsets")
    var iconsets: ArrayList<IconSet>? = null

    @SerializedName("total_count")
    var total_count: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("code")
    var code = 0
}