package com.man.hellosport.ui.league.info.slider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.man.hellosport.R
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_slider.view.*

class BannerAdapter(private val lists: List<String>) : SliderViewAdapter<BannerAdapter.BannerVH>() {
    override fun onCreateViewHolder(parent: ViewGroup?): BannerVH {
        return BannerVH(
            LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_slider, null))
    }

    override fun onBindViewHolder(viewHolder: BannerVH, position: Int) {
        Picasso.get().load(lists[position]).into(viewHolder.imageBanner)
    }

    override fun getCount(): Int = lists.size

    inner class BannerVH(view: View) : SliderViewAdapter.ViewHolder(view) {
        val imageBanner = view.imgSilder!!
    }
}