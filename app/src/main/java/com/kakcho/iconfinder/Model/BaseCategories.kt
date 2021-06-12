package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName

class BaseCategories{
    var categories: List<Category>?= null

    @SerializedName("total_count")
    var total_count: String?= null // 125

    @SerializedName("message")
    var message: String? = null

    @SerializedName("code")
    var code: Int = 0
}