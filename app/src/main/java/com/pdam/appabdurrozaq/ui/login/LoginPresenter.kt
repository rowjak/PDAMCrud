package com.pdam.appabdurrozaq.ui.login

import com.pdam.appabdurrozaq.api.ApiService
import com.pdam.appabdurrozaq.data.database.PrefsManager
import com.pdam.appabdurrozaq.data.model.user.DataLogin
import com.pdam.appabdurrozaq.data.model.user.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val view: LoginContract.View): LoginContract.Presenter{

    init {
        view.initActivity()
        view.initListener()
    }

    override fun doLogin(username: String, password: String) {
        view.onLoading(true)
        ApiService.endpoint.loginUser(username, password)
            .enqueue(object: Callback<ResponseLogin> {
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    view.onLoading(false)
                    view.showMessage(t.message.toString(), "error")
                }

                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful){
                        val responseLogin: ResponseLogin? = response.body()
                        view.showMessage(responseLogin!!.message, "success")
                        if (responseLogin!!.status){
                            view.onResult(responseLogin)
                        }
                    }
                }

            })

    }

    override fun setPrefs(prefsManager: PrefsManager, dataLogin: DataLogin) {
        prefsManager.prefsIsLogin = true
        prefsManager.prefsUsername = dataLogin.username!!
        prefsManager.prefsNama = dataLogin.nama!!
        prefsManager.prefsLevel = dataLogin.level_user!!
    }

}