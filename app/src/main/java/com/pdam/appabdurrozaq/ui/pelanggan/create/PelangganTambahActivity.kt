package com.pdam.appabdurrozaq.ui.pelanggan.create

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.pdam.appabdurrozaq.R
import com.pdam.appabdurrozaq.data.Constant
import com.pdam.appabdurrozaq.data.model.pelanggan.ResponsePelangganUpdate
import com.pdam.appabdurrozaq.ui.pelanggan.LocationPickerActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_pelanggan_tambah.*

class PelangganTambahActivity : AppCompatActivity(), PelangganTambahContract.View {

    lateinit var pelangganTambahPresenter: PelangganTambahPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelanggan_tambah)
        pelangganTambahPresenter = PelangganTambahPresenter(this)
    }

    override fun initActivity() {
        supportActionBar!!.title = "Tambah Data Pelanggan"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        edtLokasi.setOnClickListener {
            startActivityForResult(Intent(this,
                LocationPickerActivity::class.java), Constant.REQUEST_CODE)
        }

        btnCreate.setOnClickListener {
            val id_plg = edtIdPlg.text
            val nama = edtNama.text
            val alamat = edtAlamat.text
            val email = edtEmail.text
            val mobile = edtMobile.text
            val lokasi = edtLokasi.text

            if (id_plg.isNullOrEmpty() || nama.isNullOrEmpty() || alamat.isNullOrEmpty() || lokasi.isNullOrEmpty() ||
                email.isNullOrEmpty() || mobile.isNullOrEmpty()){
                MaterialDialog(this).show {
                    title(text = "Peringatan")
                    message(text = "Lengkapi Data Dengan Benar")
                    cornerRadius(16f)
                    positiveButton(text = "OK") { dialog ->
                        this.dismiss()
                    }
                }
            }else{
                pelangganTambahPresenter.savePelanggan(
                    id_plg.toString(), nama.toString(), alamat.toString(),email.toString(),mobile.toString(),
                    Constant.LATITUDE, Constant.LONGITUDE)
                Log.d("otw insert agen", "proses insert agen")
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading){
            true -> {
                loading_ripple.visibility = View.VISIBLE
                btnCreate.visibility = View.GONE
            }
            false -> {
                loading_ripple.visibility = View.GONE
                btnCreate.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responsePelangganUpdate: ResponsePelangganUpdate) {
        showMessage(responsePelangganUpdate.message, "success")
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == Constant.REQUEST_CODE && resultCode == Activity.RESULT_OK){
            edtLokasi.setText("${Constant.LATITUDE}, ${Constant.LONGITUDE}")
            tvAlamat.text = Constant.ALAMAT
        }

        super.onActivityResult(requestCode, resultCode, data)
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

    override fun onDestroy() {
        Constant.LATITUDE = ""
        Constant.LONGITUDE = ""
        Constant.ALAMAT = ""
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
