package com.pdam.appabdurrozaq.data.database

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.stringPref

class PrefsManager (context: Context) : Krate {

    private val PREF_IS_LOGIN: String = "is_login"
    private val PREF_USERNAME: String = "username"
    private val PREF_PASSWORD: String = "password"
    private val PREF_NAMA: String = "nama"
    private val PREF_LEVEL: String = "level_user"

    override val sharedPreferences: SharedPreferences

    init {
//        constructor diisi nama sharedpred / database
        sharedPreferences = context.applicationContext.getSharedPreferences(
            "pdam_prefs", Context.MODE_PRIVATE
        )

    }

    var prefsIsLogin by booleanPref(PREF_IS_LOGIN, false)
    var prefsUsername by stringPref(PREF_USERNAME, "")
    var prefsPassword by stringPref(PREF_PASSWORD, "")
    var prefsNama by stringPref(PREF_NAMA, "")
    var prefsLevel by stringPref(PREF_LEVEL, "")

    fun logout(){
        sharedPreferences.edit().clear().apply()
    }

}