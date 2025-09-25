package com.example.prosjekt1_s385550

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel

class PrefViewModel(application: Application) : AndroidViewModel(application) {

    private val prefsName = "Mine Preferanser"
    private val prefKey = "verdi"
    private val doneKey = "antallGjort"

    /** Lagre valgt antall oppgaver (5,10,15) */
    fun settPref(verdi: String) {
        val sharedPreferences: SharedPreferences =
            getApplication<Application>().getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(prefKey, verdi).apply()
    }

    /** Hent valgt antall oppgaver */
    fun hentPref(): String {
        val sharedPreferences: SharedPreferences =
            getApplication<Application>().getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        return sharedPreferences.getString(prefKey, "5") ?: "5"
    }

    /** Lagre hvor mange oppgaver brukeren allerede har gjort */
    fun settAntallGjort(verdi: Int) {
        val sharedPreferences: SharedPreferences =
            getApplication<Application>().getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt(doneKey, verdi).apply()
    }

    /** Hent antall oppgaver som allerede er gjort */
    fun hentAntallGjort(): Int {
        val sharedPreferences: SharedPreferences =
            getApplication<Application>().getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(doneKey, 0) // start på 0 hvis tom
    }
}
