package com.pdam.appabdurrozaq.ui.pelanggan

import com.google.android.gms.dynamic.IFragmentWrapper
import com.pdam.appabdurrozaq.api.ApiService
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganList
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganUpdate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PelangganPresenter (val view: PelangganContract.View) : PelangganContract.Presenter{

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getPelanggan() {
        view.onLoadingPelanggan(true)
        ApiService.endpoint.getPelanggan("-6.889836","109.674591")
            .enqueue(object: Callback<ResponsePelangganList>{
                override fun onFailure(call: Call<ResponsePelangganList>, t: Throwable) {
                    view.onLoadingPelanggan(false)
                }

                override fun onResponse(
                    call: Call<ResponsePelangganList>,
                    response: Response<ResponsePelangganList>
                ) {
                    view.onLoadingPelanggan(false)
                    if (response.isSuccessful){
                        val responsePelangganList: ResponsePelangganList? = response.body()
                        view.onResultPelanggan(responsePelangganList!!)
                    }
                }

            })
    }

    override fun deletePelanggan(id: Long) {
        view.onLoadingPelanggan(false)
        ApiService.endpoint.deletePelanggan(id)
            .enqueue(object: Callback<ResponsePelangganUpdate>{
                override fun onFailure(call: Call<ResponsePelangganUpdate>, t: Throwable) {
                    view.onLoadingPelanggan(false)
                }

                override fun onResponse(
                    call: Call<ResponsePelangganUpdate>,
                    response: Response<ResponsePelangganUpdate>
                ) {
                    view.onLoadingPelanggan(false)
                    if (response.isSuccessful){
                        val responsePelangganUpdate: ResponsePelangganUpdate? = response.body()
                        view.onResultDelete(responsePelangganUpdate!!)
                    }
                }

            })
    }

}