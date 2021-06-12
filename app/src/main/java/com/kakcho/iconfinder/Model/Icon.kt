package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class Icon{
    @SerializedName("categories")
    var categories: List<Category>? = null
    @SerializedName("containers")
    var containers: List<Container>? = null
    @SerializedName("icon_id")
    var icon_id: String ? = null// 182502
    @SerializedName("is_icon_glyph")
    var is_icon_glyph: String? = null // false
    @SerializedName("is_premium")
    var is_premium: String? = null // false
    @SerializedName("published_at")
    var published_at: String? = null // 2017-05-30T10:49:05.325Z
    @SerializedName("raster_sizes")
    var raster_sizes: List<RasterSize>? = null
    @SerializedName("styles")
    var styles: List<Style>? = null
    @SerializedName("tags")
    var tags: List<String>? = null
    @SerializedName("type")
    var type: String? = null // vector
    @SerializedName("vector_sizes")
    var vector_sizes: List<VectorSize>? = null
    var prices: ArrayList<Price>? = null
}