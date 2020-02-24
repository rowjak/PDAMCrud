package com.pdam.appabdurrozaq.data.model.user

import com.google.gson.annotations.SerializedName

data class DataLogin(

//    fungsi String? adalah data pada kolom" tersebut diizinkan untuk null atau tidak ada nilai/isinya
    @SerializedName("username") val username : String?,
    @SerializedName("password") val password : String?,
    @SerializedName("nama") val nama : String?,
    @SerializedName("level_user") val level_user : String?

)