package com.kakcho.iconfinder.Network

//import com.kakcho.iconfinder.ResponseBody.IconResponseBody
//import com.kakcho.iconfinder.ResponseBody.IconSetResponseBody
import com.kakcho.iconfinder.Model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface IconFinderAPIMethods {

    @Headers("Authorization: Bearer fNPwUHulbzmqrzhXkbttzUqOkqr3QxQlWD18Cu97ilOLU5zB1xD2jA1YC6QKd3x1")
    @GET("/v4/iconsets")
    fun getIconsets(
        @Query("count") count: String?,
        @Query("after") afterIconsetId: String?,

    ): Call<BaseIconSet?>?

    @Headers("Authorization: Bearer fNPwUHulbzmqrzhXkbttzUqOkqr3QxQlWD18Cu97ilOLU5zB1xD2jA1YC6QKd3x1")
    @GET("/v4/iconsets/{iconset_identifier}/icons")
    fun getIcons(
        @Path(value = "iconset_identifier", encoded = true) identifier: String?,
        @Query("count") count: String? = "20",
        ): Call<BaseIcons?>?

    @Headers("Authorization: Bearer fNPwUHulbzmqrzhXkbttzUqOkqr3QxQlWD18Cu97ilOLU5zB1xD2jA1YC6QKd3x1")
    @GET("/v4/categories")
    fun getCategories(
        @Query("count") count: String?,
        @Query("after") afterCategoryId: String?,
    ): Call<BaseCategories?>?

    @Headers("Authorization: Bearer fNPwUHulbzmqrzhXkbttzUqOkqr3QxQlWD18Cu97ilOLU5zB1xD2jA1YC6QKd3x1")
    @GET("/v4/categories/{category_identifier}/iconsets")
    fun getIconSetFromCategories(
        @Path(value = "category_identifier", encoded = true) identifier: String?,
        @Query("count") count: String?,
//        @Query("premium") premium: String? = "false",

    ): Call<BaseIconSet?>?

    @Headers("Authorization: Bearer fNPwUHulbzmqrzhXkbttzUqOkqr3QxQlWD18Cu97ilOLU5zB1xD2jA1YC6QKd3x1")
    @GET("/v4/icons/search")
    fun getIconsFromSearch(
        @Query("query") query: String?,
        @Query("count") count: String?,

        ): Call<BaseIcons?>?

}
