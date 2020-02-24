package com.pdam.appabdurrozaq.ui.pelanggan.create

import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganUpdate
import java.io.File

interface PelangganTambahContract {
    interface Presenter{
        fun savePelanggan(id_plg: String, nama: String, alamat: String, email: String, mobile: String, latitude: String, longitude: String)
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responsePelangganUpdate: ResponsePelangganUpdate)
        fun showMessage(message:String, tipe: String)
    }
}