package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName

class Category{
    @SerializedName("identifier")
    var identifier: String?= null // mixed
    @SerializedName("name")
    var name: String? = null // Mixed
}