package com.pdam.appabdurrozaq.ui.pelanggan

import com.pdam.appabdurrozaq.data.model.pelanggan.DataPelanggan
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganList
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganUpdate


interface PelangganContract {

    interface Presenter{
        fun getPelanggan()
        fun deletePelanggan(id: Long)
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoadingPelanggan(loading: Boolean)
        fun onResultPelanggan(responsePelangganList: ResponsePelangganList)
        fun onResultDelete(responsePelangganUpdate: ResponsePelangganUpdate)
        fun showMessage(message:String, tipe:String)
        fun showDialogDelete(dataPelanggan: DataPelanggan, position: Int)
        fun showDialogDetail(dataPelanggan: DataPelanggan, position: Int)

    }
}