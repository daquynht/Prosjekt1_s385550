package com.example.prosjekt1_s385550

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel

class PrefViewModel(application: Application) : AndroidViewModel(application) {

    fun settPref(verdi: String) {
        val sharedPreferences: SharedPreferences =
            getApplication<Application>().getSharedPreferences("Mine Preferanser", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("verdi", verdi).apply()
    }

    fun hentPref(): String {
        val sharedPreferences: SharedPreferences =
            getApplication<Application>().getSharedPreferences("Mine Preferanser", Context.MODE_PRIVATE)
        // Hvis brukeren aldri har valgt → returner "5" som standard
        return sharedPreferences.getString("verdi", "5") ?: "5"
    }
}
