package com.pdam.appabdurrozaq.ui.main

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.islamkhsh.CardSliderAdapter
import com.pdam.appabdurrozaq.R
import com.ragshion.penjualan.data.model.menu_utama.DataCarousel
import kotlinx.android.synthetic.main.main_slider_item.view.*

class CarouselAdapter(items : ArrayList<DataCarousel>) : CardSliderAdapter<DataCarousel>(items) {

//    MainCarousel
//    poster

    override fun bindView(position: Int, itemContentView: View, item: DataCarousel?) {

        item?.run {
//            ganti ini dengan picasso or dll
            Glide.with(itemContentView)
                .load("http://192.168.43.248/appabdurrozaq/uploads/"+nama_file)
                .apply(RequestOptions().fitCenter())
                .into(itemContentView.movie_poster)
//            itemContentView.movie_poster.setImageResource(poster)
//            itemContentView.movie_title.text = title
//            itemContentView.movie_overview.text = overview
        }
    }

    override fun getItemContentLayout(position: Int) = R.layout.main_slider_item
}