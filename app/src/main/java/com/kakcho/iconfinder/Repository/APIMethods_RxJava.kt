package com.kakcho.iconfinder.Repository

import com.kakcho.iconfinder.Model.*
import com.kakcho.iconfinder.Network.ResponseCallback
import com.kakcho.iconfinder.Network.RetroFitAPI_RxJava
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class APIMethods_RxJava (iconFinderApi: RetroFitAPI_RxJava){


    private var iconFinderApi = iconFinderApi

    fun getIconsFromSearch(query:String?, count:String?, callback: ResponseCallback<BaseIcons?>){
        var iconFinderApi: Observable<BaseIcons?>? = iconFinderApi.getIconsFromSearch(query, count)

            fun onResponse(
                response: BaseIcons?
            ) {
                callback.success(response)
            }

            fun onFailure(t: Throwable) {
                val iconResponseBody = BaseIcons()
                iconResponseBody.message = "Network Error"
                callback.failure(iconResponseBody)
            }
        iconFinderApi!!.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({response -> onResponse(response)}, {t -> onFailure(t) })
    }

    fun getIconSetFromCategories(category_identifier: String?, count: String?, callback: ResponseCallback<BaseIconSet?>){

        var iconFinderApi: Observable<BaseIconSet?>? = iconFinderApi.getIconSetFromCategories(category_identifier, count)

            fun onResponse(response: BaseIconSet?) {
//                if (response!!.isSuccessful()) {
                    callback.success(response)
//                } else {
//                    val iconSetResponseBody = BaseIconSet()
//                    iconSetResponseBody.code = response.code()
//                    iconSetResponseBody.message = response.message()
//                    callback.failure(iconSetResponseBody)
//                }
            }

            fun onFailure(t: Throwable?) {
                val baseIconSet = BaseIconSet()
                baseIconSet.message = "Network Error"
                callback.failure(baseIconSet)
            }

        iconFinderApi!!.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({response -> onResponse(response)}, {t -> onFailure(t) })
    }

    fun getCategories(count: String?, afterIconsetId: String?, callback: ResponseCallback<BaseCategories?>){

        var iconFinderApi: Observable<BaseCategories?>? = iconFinderApi.getCategories(count, afterIconsetId)

        fun onResponse(
            response: BaseCategories?
            ) {
//                if (response?.isSuccessful() == true) {
                    callback.success(response)
//                } else {
//                    val iconSetResponseBody = BaseCategories()
//                    iconSetResponseBody.code = response!!.code()
//                    iconSetResponseBody.message = response.message()
//                    callback.failure(iconSetResponseBody)
//                }
            }

        fun onFailure(t: Throwable?) {
                val iconSetResponseBody = BaseCategories()
                iconSetResponseBody.message = "Network Error"
                callback.failure(iconSetResponseBody)
            }

        iconFinderApi!!.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({response -> onResponse(response)}, {t -> onFailure(t) })

    }

    fun getIconsets(
        count: String?,
        afterIconsetId: String?,
        callback: ResponseCallback<BaseIconSet?>
    ) {
        var iconFinderApi: Observable<BaseIconSet?>? = iconFinderApi.getIconsets(count, afterIconsetId)


        fun onResponse(
            response: BaseIconSet?
            ) {
//                if (response.isSuccessful()) {
                    callback.success(response)
//                } else {
//                    val iconSetResponseBody = BaseIconSet()
//                    iconSetResponseBody.code = response.code()
//                    iconSetResponseBody.message = response.message()
//                    callback.failure(iconSetResponseBody)
//                }
            }

        fun onFailure(t: Throwable) {
                val iconSetResponseBody = BaseIconSet()
                iconSetResponseBody.message = "Network Error"
                callback.failure(iconSetResponseBody)
            }

        iconFinderApi!!.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({response -> onResponse(response)}, {t -> onFailure(t) })


    }

    fun getIcons(
        iconsetIdentifer: String?,
        callback: ResponseCallback<BaseIcons?>
    ) {
        var iconFinderApi: Observable<BaseIcons?>? = iconFinderApi.getIcons(iconsetIdentifer)

        fun onResponse(
            response: BaseIcons?
            ) {

                    callback.success(response)

            }

        fun onFailure(t: Throwable) {
                val iconResponseBody = BaseIcons()
                iconResponseBody.message = "Network Error"
                callback.failure(iconResponseBody)
            }


        iconFinderApi!!.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({response -> onResponse(response)}, {t -> onFailure(t) })


    }



//    companion object {
//        const val BASE_URL = "https://api.iconfinder.com/"
//        private var retrofit: Retrofit? = null
//
//        val client: Retrofit?
//            get() {
//                val interceptor = HttpLoggingInterceptor()
//                interceptor.level = HttpLoggingInterceptor.Level.BODY
//                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
//                if (retrofit == null) {
//                    retrofit = Retrofit.Builder()
//                        .baseUrl(BASE_URL)
//                        .client(client)
//                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build()
//                }
//                return retrofit
//            }
//    }
//
//    init {
//        iconFinderApi = client!!.create(RetroFitAPI_RxJava::class.java)
//    }
}
