package com.example.prosjekt1_s385550

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.prosjekt1_s385550.R

class PreferanserActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferanser)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val saveButton = findViewById<Button>(R.id.saveButton)

        // Henter ut preferanser fra SharedPreferences
        val prefs = getSharedPreferences("preferanser", Context.MODE_PRIVATE)
        val antallOppgaver = prefs.getInt("antall_oppgaver", 5)

        // Setter riktig radioknapp etter preferanse
        when (antallOppgaver) {
            5 -> radioGroup.check(R.id.radio_5)
            10 -> radioGroup.check(R.id.radio_10)
            15 -> radioGroup.check(R.id.radio_15)
        }

        //Lagre knappen
        saveButton.setOnClickListener {
            val valgtId = radioGroup.checkedRadioButtonId
            val valgtKnapp = findViewById<RadioButton>(valgtId)

            val nyttAntall = when (valgtKnapp.id) {
                R.id.radio_5 -> 5
                R.id.radio_10 -> 10
                R.id.radio_15 -> 15
                else -> 5
            }

            // Lagre preferansene i SharedPreferences
            prefs.edit().putInt("antall_oppgaver", nyttAntall).apply()

            // Lukk aktiviteten
            finish()
            }
        }

    }
