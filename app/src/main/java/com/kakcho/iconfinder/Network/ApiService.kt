package com.kakcho.iconfinder.Network

import com.kakcho.iconfinder.Model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    var iconFinderApi: IconFinderAPIMethods

    fun getIconsFromSearch(query:String?, count:String?, callback: ResponseCallback<BaseIcons?>){
        val call: Call<BaseIcons?>? =
            iconFinderApi.getIconsFromSearch(query,count)
        call?.enqueue(object : Callback<BaseIcons?> {
            override fun onResponse(
                call: Call<BaseIcons?>,
                response: Response<BaseIcons?>
            ) {
                if (response.isSuccessful()) {
                    callback.success(response.body())
                } else {
                    val iconResponseBody = BaseIcons()
                    iconResponseBody.code = response.code()
                    iconResponseBody.message = response.message()
                    callback.failure(iconResponseBody)
                }
            }

            override fun onFailure(call: Call<BaseIcons?>, t: Throwable) {
                val iconResponseBody = BaseIcons()
                iconResponseBody.message = "Network Error"
                callback.failure(iconResponseBody)
            }
        })
    }

    fun getIconSetFromCategories(category_identifier: String?, count: String?, callback: ResponseCallback<BaseIconSet?>){

        val call: Call<BaseIconSet?>? = iconFinderApi.getIconSetFromCategories(category_identifier, count)
        call?.enqueue(object : Callback<BaseIconSet?> {
            override fun onResponse(call: Call<BaseIconSet?>?, response: Response<BaseIconSet?>?) {
                if (response!!.isSuccessful()) {
                    callback.success(response.body()!!)
                } else {
                    val iconSetResponseBody = BaseIconSet()
                    iconSetResponseBody.code = response.code()
                    iconSetResponseBody.message = response.message()
                    callback.failure(iconSetResponseBody)
                }
            }

            override fun onFailure(call: Call<BaseIconSet?>?, t: Throwable?) {

                val baseIconSet = BaseIconSet()
                baseIconSet.message = "Network Error"
                callback.failure(baseIconSet)

            }


        })
    }

    fun getCategories(count: String?, afterIconsetId: String?, callback: ResponseCallback<BaseCategories?>){

        val call: Call<BaseCategories?>? = iconFinderApi.getCategories(count, afterIconsetId)
        call?.enqueue(object : Callback<BaseCategories?> {
            override fun onResponse(
                call: Call<BaseCategories?>?,
                response: Response<BaseCategories?>?
            ) {
                if (response?.isSuccessful() == true) {
                    callback.success(response.body())
                } else {
                    val iconSetResponseBody = BaseCategories()
                    iconSetResponseBody.code = response!!.code()
                    iconSetResponseBody.message = response.message()
                    callback.failure(iconSetResponseBody)
                }
            }

            override fun onFailure(call: Call<BaseCategories?>?, t: Throwable?) {
                val iconSetResponseBody = BaseCategories()
                iconSetResponseBody.message = "Network Error"
                callback.failure(iconSetResponseBody)
            }
        })
    }

    fun getIconsets(
        count: String?,
        afterIconsetId: String?,
        callback: ResponseCallback<BaseIconSet?>
    ) {
        val call: Call<BaseIconSet?>? =
            iconFinderApi.getIconsets(count, afterIconsetId)
        call?.enqueue(object : Callback<BaseIconSet?> {
            override fun onResponse(
                call: Call<BaseIconSet?>,
                response: Response<BaseIconSet?>
            ) {
                if (response.isSuccessful()) {
                    callback.success(response.body())
                } else {
                    val iconSetResponseBody = BaseIconSet()
                    iconSetResponseBody.code = response.code()
                    iconSetResponseBody.message = response.message()
                    callback.failure(iconSetResponseBody)
                }
            }

            override fun onFailure(call: Call<BaseIconSet?>, t: Throwable) {
                val iconSetResponseBody = BaseIconSet()
                iconSetResponseBody.message = "Network Error"
                callback.failure(iconSetResponseBody)
            }
        })
    }

    fun getIcons(
        iconsetIdentifer: String?,
        callback: ResponseCallback<BaseIcons?>
    ) {
        val call: Call<BaseIcons?>? =
            iconFinderApi.getIcons(iconsetIdentifer)
        call?.enqueue(object : Callback<BaseIcons?> {
            override fun onResponse(
                call: Call<BaseIcons?>,
                response: Response<BaseIcons?>
            ) {
                if (response.isSuccessful()) {
                    callback.success(response.body())
                } else {
                    val iconResponseBody = BaseIcons()
                    iconResponseBody.code = response.code()
                    iconResponseBody.message = response.message()
                    callback.failure(iconResponseBody)
                }
            }

            override fun onFailure(call: Call<BaseIcons?>, t: Throwable) {
                val iconResponseBody = BaseIcons()
                iconResponseBody.message = "Network Error"
                callback.failure(iconResponseBody)
            }
        })
    }



    companion object {
        const val BASE_URL = "https://api.iconfinder.com/"
        private var retrofit: Retrofit? = null
        val client: Retrofit?
            get() {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit
            }
    }

    init {
        iconFinderApi = client!!.create(IconFinderAPIMethods::class.java)
    }
}
