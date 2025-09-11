package com.example.prosjekt1_s385550

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainlayout)  // <-- peker til XML

        //Start Spill-knappen
        val startButton = findViewById<Button>(R.id.button_start)

        //Knappen trykkes og GameActivity åpnes
        startButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }
}
