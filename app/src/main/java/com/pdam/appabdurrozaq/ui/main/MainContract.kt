package com.pdam.appabdurrozaq.ui.main

import com.pdam.appabdurrozaq.data.database.PrefsManager
import com.ragshion.penjualan.data.model.menu_utama.ResponseCarouselList

interface MainContract {

    interface Presenter{
        fun getCardSlider()
        fun doLogout(prefsManager: PrefsManager)

    }

    interface View{
        fun initListener()
        fun onResultCardSlider(responseCarouselList: ResponseCarouselList)
        fun onLoginOr(boolean: Boolean)
        fun showMessage(message: String, tipe: String)
        fun onResultLogout()
    }
}