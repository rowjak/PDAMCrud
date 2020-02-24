package com.pdam.appabdurrozaq.ui.pelanggan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdam.appabdurrozaq.R
import com.pdam.appabdurrozaq.data.Constant
import com.pdam.appabdurrozaq.data.model.pelanggan.DataPelanggan
import kotlinx.android.synthetic.main.adapter_pelanggan.view.*

class PelangganAdapter(val context: Context, var dataPelanggan: ArrayList<DataPelanggan>,
                       val clickListener: (DataPelanggan, Int, String) -> Unit, private val adapterOnClick: (DataPelanggan, Int) -> Boolean):
        RecyclerView.Adapter<PelangganAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_pelanggan, parent, false)
    )

    override fun getItemCount() = dataPelanggan.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataPelanggan[position])

        holder.view.cardItemPelanggan.setOnLongClickListener {
            adapterOnClick(dataPelanggan[position], position)
        }
        holder.view.cardItemPelanggan.setOnClickListener {
            Constant.ID_ADAPTER= dataPelanggan[position].id!!
            clickListener(dataPelanggan[position], position, "detail")
        }

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bind(dataPelanggan: DataPelanggan){
            itemView.apply {
                view.tvNama.text = "Nama : "+dataPelanggan.nama
                view.tvAlamat.text = "Alamat : "+dataPelanggan.alamat
                view.tvMobile.text = "Nomor Hp : "+dataPelanggan.mobile
                view.tvJarak.text = dataPelanggan.jarak
            }
        }
    }

    fun notifyDataChange(newDataPelanggan: List<DataPelanggan>){
        dataPelanggan.clear()
        dataPelanggan.addAll(newDataPelanggan)
        notifyDataSetChanged()
    }

    fun removePelanggan(position: Int){
        dataPelanggan.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataPelanggan.size)
    }

}