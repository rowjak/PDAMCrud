package com.pdam.appabdurrozaq.ui.pelanggan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.list.listItems
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.pdam.appabdurrozaq.R
import com.pdam.appabdurrozaq.data.Constant
import com.pdam.appabdurrozaq.data.model.pelanggan.DataPelanggan
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganList
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganUpdate
import com.pdam.appabdurrozaq.ui.pelanggan.create.PelangganTambahActivity
import com.pdam.appabdurrozaq.ui.pelanggan.update.PelangganUpdateActivity
import com.pdam.appabdurrozaq.utils.MapsHelper
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_pelanggan.*
import kotlinx.android.synthetic.main.dialog_pelanggan.view.*

class PelangganActivity : AppCompatActivity(), PelangganContract.View, OnMapReadyCallback {

    lateinit var pelangganPresenter: PelangganPresenter
    lateinit var pelangganAdapter: PelangganAdapter
    lateinit var pelanggan:DataPelanggan


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelanggan)
        pelangganPresenter = PelangganPresenter(this)
//        pelangganPresenter.getPelanggan()
    }

    override fun initActivity() {
        supportActionBar!!.title = "Data Pelanggan"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        pelangganAdapter = PelangganAdapter(this, arrayListOf(), {
                dataPelanggan: DataPelanggan, position: Int, type: String ->
            pelanggan = dataPelanggan
            when(type){
                "detail" -> showDialogDetail(dataPelanggan, position)
            }
        }){
                dataPelanggan: DataPelanggan, position: Int ->
//            val items = listOf(
//                BasicGridItem(R.drawable.ic_adapter_edit, "Ubah"),
//                BasicGridItem(R.drawable.ic_adapter_delete, "Hapus")
//            )

            MaterialDialog(this, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
                title(text = dataPelanggan.nama)
                cornerRadius(res = R.dimen.my_corner_radius)
                listItems(items = listOf("Ubah","Hapus")) { _, index, text ->
                    Constant.ID_ADAPTER = dataPelanggan.id!!
                    if (text.equals("Ubah")){
                        startActivity(Intent(context, PelangganUpdateActivity::class.java))
                    }else{
                        showDialogDelete(dataPelanggan, position)
                    }
                }
            }

            true
        }

        rvPelanggan.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pelangganAdapter
        }

        swipe.setOnRefreshListener {
            pelangganPresenter.getPelanggan()
        }

        fab_add.setOnClickListener {
            startActivity(Intent(this, PelangganTambahActivity::class.java))
        }
    }

    override fun onLoadingPelanggan(loading: Boolean) {
        when(loading){
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultPelanggan(responsePelangganList: ResponsePelangganList) {
        val dataPelanggan: List<DataPelanggan> = responsePelangganList.dataPelanggan
        pelangganAdapter.notifyDataChange(dataPelanggan)
    }

    override fun onResultDelete(responsePelangganUpdate: ResponsePelangganUpdate) {
        showMessage(responsePelangganUpdate.message, "info")
    }

    override fun showMessage(message: String, tipe: String) {
        when {
            tipe.equals("info", ignoreCase = true) -> {
                Toasty.info(this, message).show()
            }
            tipe.equals("success", ignoreCase = true) -> {
                Toasty.success(this, message).show()
            }
            tipe.equals("warning", ignoreCase = true) -> {
                Toasty.warning(this, message).show()
            }
            else -> {
                Toasty.error(this, message).show()
            }
        }
    }

    override fun showDialogDelete(dataPelanggan: DataPelanggan, position: Int) {
        MaterialDialog(this).show {
            title(text = "Konfirmasi")
            message(text = "Anda Yakin Ingin Menghapus Data Dengan Nama Pelanggan ${dataPelanggan.nama}")
            positiveButton(text = "Yakin") { dialog ->
                pelangganPresenter.deletePelanggan(Constant.ID_ADAPTER)
                pelangganAdapter.removePelanggan(position)
                this.dismiss()
            }

            negativeButton(text = "Batal") { dialog ->
                this.dismiss()
            }

        }
    }

    override fun showDialogDetail(dataPelanggan: DataPelanggan, position: Int) {
        val dialog=  BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_pelanggan, null)

        view.tvNamaPelanggan.text = "Nama Pelanggan : "+dataPelanggan.nama
        view.tvNomor.text = "Nomor HP : "+dataPelanggan.mobile
        view.tvEmail.text = "Email : "+dataPelanggan.email
        view.tvAlamat.text = dataPelanggan.alamat

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        view.imvClose.setOnClickListener{
            supportFragmentManager.beginTransaction().remove(mapFragment).commit()
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(pelanggan.latitude!!.toDouble(), pelanggan.longitude!!.toDouble())
        googleMap.addMarker(MarkerOptions().position(latLng).title(pelanggan.nama))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        pelangganPresenter.getPelanggan()
    }

}
