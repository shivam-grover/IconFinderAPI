package com.kakcho.iconfinder.Repository

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kakcho.iconfinder.Model.*
import com.kakcho.iconfinder.Network.ApiService
import com.kakcho.iconfinder.Network.ResponseCallback

class MainActivityRepository (val application: Application) {

    var iconsetsList = ArrayList<IconSet>()
    var iconsList = ArrayList<Icon>()
    var categoriesList = ArrayList<Category>()
    var afterIconsetId = ""
    var currentChildPos = 0

    var iconsetsListLive = MutableLiveData<ArrayList<IconSet>>()
    var iconsListLive = MutableLiveData<ArrayList<Icon>>()
    var categoriesListLive = MutableLiveData<ArrayList<Category>>()

    var triggerLoader = MutableLiveData<Boolean>()
    var populateRV = MutableLiveData<Boolean>()

    var apiService: ApiService = ApiService()

    fun getCategories(){

        apiService.getCategories("100", "", object : ResponseCallback<BaseCategories?> {
            override fun success(t: BaseCategories?) {

                for (i in 0 until t?.categories!!.size) {
                    categoriesList!!.add(t.categories!!.get(i))
                    Log.e("CATEGORY " + i, t.categories!!.get(i).name.toString())
                }
                categoriesListLive.value = categoriesList
                getIconSetFromCategory()
            }
            override fun failure(t: BaseCategories?) {
                Toast.makeText(application,t?.message.toString() + "",Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getIconSetFromCategory(){
        val num_categories = categoriesList.size
        Log.e("I AM IN ICONSET", num_categories.toString())
        var rnds = (0..num_categories).random()
        if(rnds == 68 || rnds == 97){
            rnds += 1
        }
        for (i in 0 until categoriesList.size){
            if(i==rnds){
                var categoryIdentifier = categoriesList.get(i).identifier

                apiService.getIconSetFromCategories(
                    categoryIdentifier, "10",
                    object : ResponseCallback<BaseIconSet?> {

                        override fun success(iconSetResponseBody: BaseIconSet?) {
                            for (i in 0 until iconSetResponseBody?.iconsets!!.size) {
                                iconsetsList!!.add(iconSetResponseBody.iconsets!!.get(i))
                                Log.d("LOOP MAIN", iconSetResponseBody.iconsets!!.get(i).toString())
                                populateIconsList(iconSetResponseBody.iconsets!!.get(i).identifier)
                            }
                            iconsetsListLive.value = iconsetsList
                            if(iconSetResponseBody.iconsets!!.size>0){
                                afterIconsetId = iconSetResponseBody.iconsets!!
                                    .get(iconSetResponseBody.iconsets!!.size - 1).iconset_id!!}
                            else{
                                getIconSetFromCategory()
                            }
//                            if (currentChildPos == 0) {
                                populateRV.value = (populateRV.value != null && populateRV.value!!)
//                                populateRecyclerIconView()
//                            }
                        }

                        override fun failure(iconSetResponseBody: BaseIconSet?) {
                            Toast.makeText(
                                application,
                                iconSetResponseBody?.message.toString() + "",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )}

        }
    }

    private fun populateIconsList(iconsetIdentifer: String?) {

        apiService.getIcons(
            iconsetIdentifer,
            object : ResponseCallback<BaseIcons?> {

                override fun success(iconResponseBody: BaseIcons?) {
                    for (icon in iconResponseBody?.icons!!) {
                        iconsList!!.add(icon)
                        Log.v("iconname", icon.icon_id.toString())
                    }
                    iconsListLive.value = iconsList
                    triggerLoader.value = (triggerLoader.value != null && triggerLoader.value!!)
//                    iconAdapter.notifyItemInserted(iconsList!!.size - 1)
//                    loader.visibility = View.GONE
//                    loader.pauseAnimation()
                }

                override fun failure(iconResponseBody: BaseIcons?) {
                    Toast.makeText(application,iconResponseBody?.message.toString() + "",
                        Toast.LENGTH_SHORT).show()
                }
            })

    }

    fun populateIconsListUsingQuery(query: String?){
        clearLists()
        apiService.getIconsFromSearch(
            query,
            "40",
            object : ResponseCallback<BaseIcons?> {
                override fun success(iconResponseBody: BaseIcons?) {
                    for (icon in iconResponseBody?.icons!!) {
                        iconsList!!.add(icon)
                        Log.v("iconname", icon.icon_id.toString())
                    }
                    iconsListLive.value = iconsList
                    triggerLoader.value = (triggerLoader.value != null && triggerLoader.value!!)
//                    noIconsFoundText!!.visibility = View.GONE
//                    iconRecycler!!.visibility = View.VISIBLE
//                    populateRV.value = !(populateRV.value!!)
                    populateRV.value = (populateRV.value != null && populateRV.value!!)

//                    populateRecyclerIconView()
                }

                override fun failure(iconResponseBody: BaseIcons?) {
//                    noIconsFoundText!!.visibility = View.VISIBLE
//                    iconRecycler!!.visibility = View.GONE
                }
            })
    }

    fun clearLists(){
        iconsList.clear()
        iconsetsList.clear()
        iconsListLive.value = iconsList
        iconsetsListLive.value = iconsetsList
    }


}