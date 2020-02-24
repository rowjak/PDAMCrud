package com.pdam.appabdurrozaq.ui.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.pdam.appabdurrozaq.R
import com.pdam.appabdurrozaq.data.database.PrefsManager
import com.pdam.appabdurrozaq.data.model.user.ResponseLogin
import com.pdam.appabdurrozaq.ui.main.MainActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var loginPresenter: LoginPresenter
    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginPresenter = LoginPresenter(this)
        prefsManager = PrefsManager(this)
    }

    override fun initActivity() {
        GlideToVectorYou.justLoadImage(this, Uri.parse("http://192.168.43.248/appabdurrozaq/uploads/android_img/signin.svg"), iv_login)
    }

    override fun initListener() {
        btnLogin.setOnClickListener {
            loginPresenter.doLogin(edtUsername.text.toString(), edtPassword.text.toString())
        }
    }

    override fun onLoading(loading: Boolean) {
    }

    override fun onResult(responseLogin: ResponseLogin) {
        loginPresenter.setPrefs(prefsManager, responseLogin.data!!)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
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
}
