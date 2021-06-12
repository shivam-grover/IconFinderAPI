package com.kakcho.iconfinder.Views

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
//import android.support.v4.app.ActivityCompat
//import android.support.v7.app.ActionBar
//import android.support.v7.widget.DefaultItemAnimator
//import android.support.v7.widget.GridLayoutManager
//import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.kakcho.iconfinder.RecyclerAdapter.IconAdapter
import com.kakcho.iconfinder.Model.*

import com.kakcho.iconfinder.R

import com.kakcho.iconfinder.Network.ApiService
import com.kakcho.iconfinder.Network.ResponseCallback
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var searchbtn: ImageButton
    lateinit var cross: ImageButton
    lateinit var querytext: EditText
    lateinit var IconImg: ImageView
    lateinit var iconTitle: TextView
    lateinit var downloadmanager: DownloadManager
    lateinit var iconAdapter: IconAdapter
    lateinit var iconRecycler: RecyclerView
    lateinit var noIconsFoundText: TextView
    lateinit var loader: LottieAnimationView
    lateinit var iconName: String
    var isQuerySearch = false
    var afterIconsetId = ""
    lateinit var apiService: ApiService
    lateinit var iconsetsList: ArrayList<IconSet>
    var currentChildPos = 0
    lateinit var iconsList: ArrayList<Icon>
    lateinit var categoriesList: ArrayList<Category>
    lateinit var downloadUrl: String
    lateinit var searchbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchbutton = findViewById(R.id.search)
        searchbutton.visibility = View.GONE
        iconRecycler = findViewById(R.id.iconsRv)
        noIconsFoundText = findViewById(R.id.noIconsFoundTv)
        loader = findViewById(R.id.loaderlottie)
        val actionBar: androidx.appcompat.app.ActionBar? = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true)
            actionBar.setIcon(R.drawable.ic_launcher_background)
            actionBar.setDisplayShowTitleEnabled(false)
        }
        val inflator = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = inflator.inflate(R.layout.custom_app_bar, null)
        searchbtn = v.findViewById(R.id.searchBtn)
        cross = v.findViewById(R.id.crossBtn)
        querytext = v.findViewById(R.id.searchIconEt)
        IconImg = v.findViewById(R.id.appIconImageView)
        iconTitle = v.findViewById(R.id.appTitle)
        searchbtn.setOnClickListener{
//            searchbutton.visibility = View.VISIBLE
            searchbtn.setVisibility(View.GONE)
            cross.setVisibility(View.VISIBLE)
            querytext.setVisibility(View.VISIBLE)
            IconImg.setVisibility(View.GONE)
            iconTitle.setVisibility(View.GONE)
        }
        cross.setOnClickListener(View.OnClickListener {
            val letters = querytext.text.length
            querytext.setText("")
            cross.setVisibility(View.GONE)
            querytext.setVisibility(View.GONE)
            IconImg.setVisibility(View.VISIBLE)
            iconTitle.setVisibility(View.VISIBLE)
            searchbtn.setVisibility(View.VISIBLE)
            currentChildPos = 0
            if(letters>2){
            iconsList!!.clear()
            iconsetsList!!.clear()}
            iconRecycler!!.visibility = View.VISIBLE
            noIconsFoundText!!.visibility = View.GONE
            afterIconsetId = ""
            isQuerySearch = false
            iconAdapter.notifyDataSetChanged()
            if(letters>2) {
                getIconSetFromCategory()
            }
        })

//        searchbutton.setOnClickListener{
//            val text = querytext.text
//            populateIconsListUsingQuery(text.toString().trim { it <= ' ' })
//            iconsList!!.clear()
//            iconsetsList!!.clear()
//            isQuerySearch = true
//        }

        querytext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length > 2) {
                    populateIconsListUsingQuery(s.toString().trim { it <= ' ' })
                    iconsList!!.clear()
                    iconsetsList!!.clear()
                    isQuerySearch = true
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        actionBar?.setCustomView(v)
        apiService = ApiService()
        iconsetsList = ArrayList<IconSet>()
        iconsList = ArrayList<Icon>()
        categoriesList = ArrayList<Category>()
        var afterCategories = ""
        getCategories()
    }

    private fun getCategories() {
        apiService.getCategories("100", "", object : ResponseCallback<BaseCategories?>{
            override fun success(t: BaseCategories?) {

                for (i in 0 until t?.categories!!.size) {
                    categoriesList!!.add(t.categories!!.get(i))
                    Log.e("CATEGORY " + i, t.categories!!.get(i).name.toString())
//                    populateIconsList(iconSetResponseBody.iconsets!!.get(i).identifier)
                }
                getIconSetFromCategory()
            }

            override fun failure(t: BaseCategories?) {
                Toast.makeText(
                    this@MainActivity,
                    t?.message.toString() + "",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun getIconSetFromCategory(){
//        iconsLoadingBar!!.start()
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
                        if(iconSetResponseBody.iconsets!!.size>0){
                        afterIconsetId = iconSetResponseBody.iconsets!!
                            .get(iconSetResponseBody.iconsets!!.size - 1).iconset_id!!}
                        else{
                            getIconSetFromCategory()
                        }
                        if (currentChildPos == 0) populateRecyclerIconView()
                    }

                    override fun failure(iconSetResponseBody: BaseIconSet?) {
                        Toast.makeText(
                            this@MainActivity,
                            iconSetResponseBody?.message.toString() + "",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )}

        }


    }

    fun populateIconsList(iconsetIdentifer: String?) {
        apiService.getIcons(
            iconsetIdentifer,
            object : ResponseCallback<BaseIcons?> {

                override fun success(iconResponseBody: BaseIcons?) {
                    for (icon in iconResponseBody?.icons!!) {
                        iconsList!!.add(icon)
                        Log.v("iconname", icon.icon_id.toString())
                    }
                    iconAdapter.notifyItemInserted(iconsList!!.size - 1)
//                    iconsLoadingBar!!.stop()
                    loader.visibility = View.GONE
                    loader.pauseAnimation()
                }

                override fun failure(iconResponseBody: BaseIcons?) {
                    Toast.makeText(
                        this@MainActivity,
                        iconResponseBody?.message.toString() + "",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun populateRecyclerIconView() {
        iconAdapter = object : IconAdapter(this, iconsList) {
            override fun load(position: Int) {
                if (!isQuerySearch) {
//                    populateIconsetsList()
                    Log.d("CHANGING POSITION", "currentChildPos = " + currentChildPos + "  position = " + position)
                    getIconSetFromCategory()
                    currentChildPos = position
                }
            }

            override fun downloadIcon(url: String?, iconName1: String?) {
                if (url != null) {
                    downloadUrl = url
                }
                if (iconName1 != null) {
                    iconName = iconName1
                }
                isStoragePermissionGranted
            }
        }
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this@MainActivity, 2)
        iconRecycler!!.layoutManager = mLayoutManager
        iconRecycler!!.itemAnimator = DefaultItemAnimator()
        iconRecycler!!.adapter = iconAdapter
    }

    //permission is automatically granted on sdk<23 upon installation
    val isStoragePermissionGranted: Boolean
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                === PackageManager.PERMISSION_GRANTED
            ) {
                Log.v("TAG", "Permission is granted")
                downloadIconManager()
                true
            } else {
                Log.v("TAG", "Permission is revoked")
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted")
            downloadIconManager()
            true
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("TAG", "Permission: " + permissions[0] + "was " + grantResults[0])
            downloadIconManager()
        }
    }

    fun populateIconsListUsingQuery(query: String?) {
        apiService.getIconsFromSearch(
            query,
            "40",
            object : ResponseCallback<BaseIcons?> {
                override fun success(iconResponseBody: BaseIcons?) {
                    for (icon in iconResponseBody?.icons!!) {
                        iconsList!!.add(icon)
                        Log.v("iconname", icon.icon_id.toString())
                    }
                    noIconsFoundText!!.visibility = View.GONE
                    iconRecycler!!.visibility = View.VISIBLE
                    populateRecyclerIconView()
                }

                override fun failure(iconResponseBody: BaseIcons?) {
                    noIconsFoundText!!.visibility = View.VISIBLE
                    iconRecycler!!.visibility = View.GONE
                }
            })
    }

    fun downloadIconManager() {
        downloadmanager = (getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?)!!
        val uri = Uri.parse(downloadUrl)
        Log.v("downloadUrl1", downloadUrl!!)
        Log.v("downloaduri1", uri.toString())
        val request = DownloadManager.Request(uri)
        request.setTitle(iconName)
        request.setDescription("Downloading")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$iconName.png")
        downloadmanager!!.enqueue(request)
//        Toast.makeText(this, "Downloading icon!", Toast.LENGTH_SHORT).show()
    }
}