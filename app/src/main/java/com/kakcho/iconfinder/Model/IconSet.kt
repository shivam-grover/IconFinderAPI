package com.kakcho.iconfinder.Model


import com.google.gson.annotations.SerializedName

class IconSet{
    @SerializedName("are_all_icons_glyph")
    var are_all_icons_glyph: Boolean = false // false
    @SerializedName("author")
    var author: Author ?= null

    @SerializedName("categories")
    var categories: List<Category> ?= null

    @SerializedName("icons_count")
    var icons_count: String? = null // 25
    @SerializedName("iconset_id")
    var iconset_id: String? = null // 195129
    @SerializedName("identifier")
    var identifier: String? = null // insurance-101
    @SerializedName("is_premium")
    var is_premium: Boolean = false // true
    @SerializedName("name")
    var name: String? = null // Insurance
    @SerializedName("prices")
    var prices: List<Price>? = null
    @SerializedName("published_at")
    var published_at: String? = null // 2020-06-29T11:37:05.491Z
    @SerializedName("styles")
    var styles: List<Style>? = null

    @SerializedName("type")
    var type: String? = null // vector
}