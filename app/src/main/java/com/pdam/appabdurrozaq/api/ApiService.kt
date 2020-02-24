package com.pdam.appabdurrozaq.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

//    mengisi base_url : alamt ip
    var BASE_URL : String = "http://35.240.163.37/appabdurrozaq/api/"
//    memanggil class endpoint.. yaitu lokasi terakhir dari akses api
    val endpoint: ApiEndPoint
    get(){
//        interceptor, logging data dari api
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiEndPoint::class.java)
    }

}