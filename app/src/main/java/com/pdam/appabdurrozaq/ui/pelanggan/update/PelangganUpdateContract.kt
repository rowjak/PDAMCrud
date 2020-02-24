package com.pdam.appabdurrozaq.ui.pelanggan.update

import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganDetail
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganUpdate
import java.io.File

interface PelangganUpdateContract {
    interface Presenter{
        fun getDetail(id: Long)
        fun updatePelanggan(id: Long,id_plg: String, nama: String, alamat: String, email: String, mobile: String, latitude: String, longitude: String)
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultDetail(responsePelangganDetail: ResponsePelangganDetail)
        fun onResultUpdate(responsePelangganUpdate: ResponsePelangganUpdate)
        fun showMessage(message:String, tipe: String)
    }
}