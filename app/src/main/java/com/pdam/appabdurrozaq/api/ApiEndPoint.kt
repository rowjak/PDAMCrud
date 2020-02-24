package com.pdam.appabdurrozaq.api

import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganDetail
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganList
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganUpdate
import com.pdam.appabdurrozaq.data.model.user.ResponseLogin
import com.ragshion.penjualan.data.model.menu_utama.ResponseCarouselList
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint{

    @FormUrlEncoded
    @POST("user_login")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @GET("load_carousel")
    fun getCarousel(): Call<ResponseCarouselList>

    @GET("load_pelanggan")
    fun getPelanggan(
        @Query("lat") lat: String,
        @Query("lng") lng: String
    ): Call<ResponsePelangganList>

    @FormUrlEncoded
    @POST("save_pelanggan")
    fun savePelanggan(
        @Field("id_plg") id_plg : String,
        @Field("nama") nama : String,
        @Field("alamat") alamat : String,
        @Field("email") email : String,
        @Field("mobile") mobile : String,
        @Field("latitude") latitude : String,
        @Field("longitude") longitude : String
    ): Call<ResponsePelangganUpdate>

    @FormUrlEncoded
    @POST("update_pelanggan")
    fun updatePelanggan(
        @Field("id") id: Long,
        @Field("id_plg") id_plg : String,
        @Field("nama") nama : String,
        @Field("alamat") alamat : String,
        @Field("email") email : String,
        @Field("mobile") mobile : String,
        @Field("latitude") latitude : String,
        @Field("longitude") longitude : String
    ): Call<ResponsePelangganUpdate>


    @GET("detail_pelanggan")
    fun detailPelanggan(
        @Query("id") id: Long
    ): Call<ResponsePelangganDetail>

//
//    @Multipart
//    @POST("agen")
//    fun insertAgent(
//        @Query("nama_toko") nama_toko: String,
//        @Query("nama_pemilik") nama_pemilik: String,
//        @Query("alamat") alamat: String,
//        @Query("latitude") latitude: String,
//        @Query("longitude") longitude: String,
//        @Part gambar_toko: MultipartBody.Part
//    ): Call<ResponseAgenUpdate>
//
//    @GET("agen/{kd_agen}")
//    fun getAgenDetail(
//        @Path("kd_agen") kd_agen: Long
//    ): Call<ResponseAgenDetail>
//
//    @Multipart
//    @POST("agen/{kd_agen}")
//    fun updateAgen(
//        @Path("kd_agen") kd_agen: Long,
//        @Query("nama_toko") nama_toko: String,
//        @Query("nama_pemilik") nama_pemilik: String,
//        @Query("alamat") alamat: String,
//        @Query("latitude") latitude: String,
//        @Query("longitude") longitude: String,
//        @Part gambar_toko: MultipartBody.Part,
//        @Query("_method") _method: String
//    ): Call<ResponseAgenUpdate>
//
    @GET("delete_pelanggan")
    fun deletePelanggan(
        @Query("id") id: Long
    ): Call<ResponsePelangganUpdate>

}
