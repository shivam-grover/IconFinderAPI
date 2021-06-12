package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName

class Price{

    @SerializedName("currency")
    var currency: String? = null // USD
    @SerializedName("license")
    var license: License?= null
    @SerializedName("price")
    var price: String? = null // 25

}