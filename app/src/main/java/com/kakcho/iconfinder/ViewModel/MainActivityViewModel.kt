package com.kakcho.iconfinder.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kakcho.iconfinder.Model.Category
import com.kakcho.iconfinder.Model.Icon
import com.kakcho.iconfinder.Model.IconSet
import com.kakcho.iconfinder.Repository.MainActivityRepository

class MainActivityViewModel (application: Application) : AndroidViewModel(application) {

    private val mainActivityRepository = MainActivityRepository(application)

    var iconsetsList : LiveData<ArrayList<IconSet>>
    var iconsList : LiveData <ArrayList<Icon>>
    var categoriesList : LiveData<ArrayList<Category>>
    var triggerLoader : LiveData<Boolean>
    var populateRV : LiveData<Boolean>
    init {
        this.iconsList = mainActivityRepository.iconsListLive
        this.iconsetsList = mainActivityRepository.iconsetsListLive
        this.categoriesList = mainActivityRepository.categoriesListLive
        this.triggerLoader = mainActivityRepository.triggerLoader
        this.populateRV = mainActivityRepository.populateRV
    }

    fun getCategories(){
        mainActivityRepository.getCategories()
    }

    fun populateIconsListUsingQuery(query: String?){
        mainActivityRepository.populateIconsListUsingQuery(query)
    }

    fun getIconSetFromCategory(){
        mainActivityRepository.getIconSetFromCategory()
    }

    fun clearLists(){
        mainActivityRepository.clearLists()
    }

}