package com.kakcho.iconfinder.RecyclerAdapter


import android.content.Context
import android.graphics.Typeface
import android.util.Log
//import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kakcho.iconfinder.Model.Icon
import com.kakcho.iconfinder.R
import com.bumptech.glide.Glide
import java.util.*


abstract class IconAdapter(var context: Context, iconsList: ArrayList<Icon>) :
    RecyclerView.Adapter<IconAdapter.IconViewHolder>() {
    var iconsList: ArrayList<Icon>
    val apiClientId: String = "V0TvTIwhJnKM9BxpClG5cWbZETAvAwkH8Ck5gMktny1BnCS8ywlopXLDt7Gvb1FS"
    val apiClientSecret: String = " Bearer fNPwUHulbzmqrzhXkbttzUqOkqr3QxQlWD18Cu97ilOLU5zB1xD2jA1YC6QKd3x1"
    val baseUrl: String = "https://api.iconfinder.com/"
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): IconViewHolder {
        context = viewGroup.context
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card, viewGroup, false)
        return IconViewHolder(view)
    }

    override fun onBindViewHolder(iconViewHolder: IconViewHolder, i: Int) {
        val icon: Icon = iconsList[i]
        Log.d("IN BIND HOLDER", icon.toString())

        Glide.with(context).load(
            icon.raster_sizes?.get(icon.raster_sizes!!.size - 1)?.formats?.get(0)
                ?.preview_url
        )
            .into(iconViewHolder.iconPreview)

        Log.d("icon_ID " + icon.icon_id, icon.is_premium.toString())

        if (icon.is_premium == "true") {
            iconViewHolder.iconDownloadBtn.visibility = View.GONE
            iconViewHolder.priceTag.visibility = View.VISIBLE
            iconViewHolder.priceTv.visibility = View.VISIBLE
            iconViewHolder.priceTv.setText(
                icon.prices?.get(0)!!.currency.toString() + " " + icon.prices?.get(0)!!
                    .price
            )
        } else {
            iconViewHolder.iconDownloadBtn.visibility = View.VISIBLE
            iconViewHolder.priceTag.visibility = View.GONE
            iconViewHolder.priceTv.visibility = View.GONE
        }

        var titleText = icon.tags!!.get(0).toString().uppercase()

        if(titleText.length>8){
            titleText = titleText.substring(0,8) + "..."
        }

        iconViewHolder.iconTitleTv.setText(titleText)
        iconViewHolder.iconDownloadBtn.setOnClickListener {
            val imageUrl: String = icon.raster_sizes?.get(icon.raster_sizes!!.size - 1)!!.formats?.get(0)!!.preview_url.toString()
            val iconName: String? = icon.tags!!.get(0) + "_" + icon.icon_id
            downloadIcon(imageUrl, iconName + ".png")
        }
        val custom_font = Typeface.createFromAsset(context.assets, "fonts/lato-heavy.ttf")
        iconViewHolder.iconTitleTv.setTypeface(custom_font)
        if (i == iconsList.size - 1) {
            load(iconsList.size)
        }
    }

    override fun getItemCount(): Int {
        return iconsList.size
    }

    inner class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iconPreview: ImageView
        var iconTitleTv: TextView
        var iconDownloadBtn: Button
        var priceTag: ImageView
        var priceTv: TextView

        init {
            iconPreview = itemView.findViewById(R.id.iconPreview)
            iconTitleTv = itemView.findViewById(R.id.iconTitleTv)
            iconDownloadBtn = itemView.findViewById(R.id.downloadIconBtn)
            priceTag = itemView.findViewById(R.id.priceTagView)
            priceTv = itemView.findViewById(R.id.priceTv)
        }
    }

    abstract fun load(position: Int)
    abstract fun downloadIcon(downloadUrl: String?, iconName: String?)

    init {
        this.iconsList = iconsList
    }
}
