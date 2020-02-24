package com.pdam.appabdurrozaq.ui.pelanggan.create

import android.util.Log
import com.pdam.appabdurrozaq.api.ApiService
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganUpdate
import com.pdam.appabdurrozaq.ui.pelanggan.PelangganContract
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PelangganTambahPresenter (val view: PelangganTambahContract.View) : PelangganTambahContract.Presenter{

    init {
        view.initActivity()
        view.initListener()
    }

    override fun savePelanggan(
        id_plg: String,
        nama: String,
        alamat: String,
        email: String,
        mobile: String,
        latitude: String,
        longitude: String
    ) {

        view.onLoading(true)
        ApiService.endpoint.savePelanggan(id_plg, nama, alamat, email, mobile, latitude, longitude)
            .enqueue(object : Callback<ResponsePelangganUpdate>{
                override fun onFailure(call: Call<ResponsePelangganUpdate>, t: Throwable) {
                    view.onLoading(false)
                }

                override fun onResponse(
                    call: Call<ResponsePelangganUpdate>,
                    response: Response<ResponsePelangganUpdate>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful){
                        val responsePelangganUpdate: ResponsePelangganUpdate? = response.body()
                        view.onResult(responsePelangganUpdate!!)
                    }
                }

            })
    }
}