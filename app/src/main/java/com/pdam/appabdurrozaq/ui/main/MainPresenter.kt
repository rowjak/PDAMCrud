package com.pdam.appabdurrozaq.ui.main

import com.pdam.appabdurrozaq.api.ApiService
import com.pdam.appabdurrozaq.data.database.PrefsManager
import com.ragshion.penjualan.data.model.menu_utama.ResponseCarouselList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter (val view: MainContract.View): MainContract.Presenter{
    init {
//        view.initActivity()
        view.initListener()
    }

    override fun getCardSlider() {
        ApiService.endpoint.getCarousel()
            .enqueue(object : Callback<ResponseCarouselList> {
                override fun onFailure(call: Call<ResponseCarouselList>, t: Throwable) {
                }
                override fun onResponse(
                    call: Call<ResponseCarouselList>,
                    response: Response<ResponseCarouselList>
                ) {
                    if (response.isSuccessful){
                        val responseCarouselList: ResponseCarouselList? = response.body()
                        view.onResultCardSlider(responseCarouselList!!)
                    }
                }


            })
    }

    override fun doLogout(prefsManager: PrefsManager) {
        prefsManager.logout()
        view.showMessage("Berhasil Keluar","info")
        view.onResultLogout()
    }
}