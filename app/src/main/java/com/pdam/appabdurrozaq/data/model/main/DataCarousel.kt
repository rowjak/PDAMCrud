package com.ragshion.penjualan.data.model.menu_utama


import com.google.gson.annotations.SerializedName

data class DataCarousel(
    @SerializedName("id") val id: String,
    @SerializedName("keterangan") val keterangan: String,
    @SerializedName("nama_file") val nama_file: String?
)