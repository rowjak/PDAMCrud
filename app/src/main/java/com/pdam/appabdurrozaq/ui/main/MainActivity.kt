package com.pdam.appabdurrozaq.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pdam.appabdurrozaq.R
import com.pdam.appabdurrozaq.data.database.PrefsManager
import com.pdam.appabdurrozaq.ui.login.LoginActivity
import com.pdam.appabdurrozaq.ui.pelanggan.MapsActivity
import com.pdam.appabdurrozaq.ui.pelanggan.PelangganActivity
import com.pdam.appabdurrozaq.ui.pelanggan.create.PelangganTambahActivity
import com.pdam.appabdurrozaq.utils.MapsHelper
import com.ragshion.penjualan.data.model.menu_utama.DataCarousel
import com.ragshion.penjualan.data.model.menu_utama.ResponseCarouselList
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_header.*

class MainActivity : AppCompatActivity(), MainContract.View {
    lateinit var mainPresenter: MainPresenter
    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefsManager = PrefsManager(this)
        mainPresenter = MainPresenter(this)
//        mainPresenter.getCardSlider()
    }

    override fun onStart() {
        if (prefsManager.prefsIsLogin){
            mainPresenter.getCardSlider()
            onLoginOr(true)
        }else{
            onLoginOr(false)
        }
        super.onStart()
    }

    override fun initListener() {

        MapsHelper.permissionMap(this, this)

        cardPelanggan.setOnClickListener {
            startActivity(Intent(this, PelangganActivity::class.java))
        }

        cardPeta.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        cardTambahPelanggan.setOnClickListener {
            startActivity(Intent(this, PelangganTambahActivity::class.java))
        }

        cardLogout.setOnClickListener {
            mainPresenter.doLogout(prefsManager)
        }
    }

    override fun onResultCardSlider(responseCarouselList: ResponseCarouselList) {
        val dataCarousel: ArrayList<DataCarousel> = responseCarouselList.data
        if (dataCarousel.isNullOrEmpty()) {

        } else {
            viewPager.adapter = CarouselAdapter(dataCarousel)
        }
    }

    override fun onLoginOr(login: Boolean) {
        when(login){
            true -> {

            }
            false -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
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

    override fun onResultLogout() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
