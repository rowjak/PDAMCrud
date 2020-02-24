package com.pdam.appabdurrozaq.data.model.user

import com.google.gson.annotations.SerializedName
import com.pdam.appabdurrozaq.data.model.user.DataLogin

data class ResponseLogin(

    @SerializedName("status") val status : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : DataLogin

)