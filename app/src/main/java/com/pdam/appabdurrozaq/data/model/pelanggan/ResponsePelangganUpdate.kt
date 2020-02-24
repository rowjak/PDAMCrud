package com.pdam.appabdurrozaq.data.model.pelanggan

import com.google.gson.annotations.SerializedName

data class ResponsePelangganUpdate(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String
)