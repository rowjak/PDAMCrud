package com.pdam.appabdurrozaq.data.model.pelanggan

import com.google.gson.annotations.SerializedName

data class DataPelanggan (

    @SerializedName("id") val id: Long?,
    @SerializedName("id_plg") val id_plg: String?,
    @SerializedName("nama") val nama: String?,
    @SerializedName("alamat") val alamat: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("mobile") val mobile: String?,
    @SerializedName("jarak") val jarak: String?,
    @SerializedName("latitude") val latitude: String?,
    @SerializedName("longitude") val longitude: String?

)