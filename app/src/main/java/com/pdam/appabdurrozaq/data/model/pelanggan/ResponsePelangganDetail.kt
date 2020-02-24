package com.pdam.appabdurrozaq.data.model.pelanggan

import com.google.gson.annotations.SerializedName

data class ResponsePelangganDetail(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val dataPelanggan: DataPelanggan
)