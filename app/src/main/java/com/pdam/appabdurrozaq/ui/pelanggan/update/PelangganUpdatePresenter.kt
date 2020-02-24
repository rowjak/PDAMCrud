package com.pdam.appabdurrozaq.ui.pelanggan.update

import android.util.Log
import com.pdam.appabdurrozaq.api.ApiService
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganDetail
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganUpdate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PelangganUpdatePresenter (val view: PelangganUpdateContract.View) : PelangganUpdateContract.Presenter{

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getDetail(id: Long) {
        view.onLoading(true)
        ApiService.endpoint.detailPelanggan(id).enqueue(object : Callback<ResponsePelangganDetail>{
            override fun onFailure(call: Call<ResponsePelangganDetail>, t: Throwable) {
                view.onLoading(false)
            }

            override fun onResponse(
                call: Call<ResponsePelangganDetail>,
                response: Response<ResponsePelangganDetail>
            ) {
                view.onLoading(false)
                if (response.isSuccessful){
                    val responsePelangganDetail: ResponsePelangganDetail? = response.body()
                    view.onResultDetail(responsePelangganDetail!!)
                }
            }

        })
    }

    override fun updatePelanggan(
        id: Long,
        id_plg: String,
        nama: String,
        alamat: String,
        email: String,
        mobile: String,
        latitude: String,
        longitude: String
    ) {

        view.onLoading(true)
        ApiService.endpoint.updatePelanggan(id, id_plg, nama, alamat, email, mobile, latitude, longitude)
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
                        view.onResultUpdate(responsePelangganUpdate!!)
                    }
                }

            })
    }
}