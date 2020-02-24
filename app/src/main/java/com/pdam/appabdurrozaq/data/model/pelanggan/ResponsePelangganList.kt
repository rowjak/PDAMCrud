package com.pdam.appabdurrozaq.data.model.pelanggan

import com.google.gson.annotations.SerializedName

data class ResponsePelangganList(
    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val dataPelanggan: List<DataPelanggan>
)