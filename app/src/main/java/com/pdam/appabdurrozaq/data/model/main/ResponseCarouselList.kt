package com.ragshion.penjualan.data.model.menu_utama

import com.google.gson.annotations.SerializedName

data class ResponseCarouselList(
    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val data: ArrayList<DataCarousel>

)