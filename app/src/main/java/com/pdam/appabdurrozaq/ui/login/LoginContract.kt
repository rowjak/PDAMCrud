package com.pdam.appabdurrozaq.ui.login

import com.pdam.appabdurrozaq.data.database.PrefsManager
import com.pdam.appabdurrozaq.data.model.user.DataLogin
import com.pdam.appabdurrozaq.data.model.user.ResponseLogin

interface LoginContract {

    interface Presenter{
        fun doLogin(username: String, password: String)
        fun setPrefs(prefsManager: PrefsManager, dataLogin: DataLogin)
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseLogin: ResponseLogin)
        fun showMessage(message: String, tipe: String)
    }
}