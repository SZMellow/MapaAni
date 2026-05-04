package com.example.mapaani

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find buttons by their ID from your activity_main.xml
        val btnFarmer = findViewById<Button>(R.id.button)
        val btnBuyer = findViewById<Button>(R.id.button2)

        // Set the action for the Farmer button
        btnFarmer.setOnClickListener {
            val intent = Intent(this, loginactivity::class.java)
            startActivity(intent)
        }

        // Set the action for the Buyer button
        btnBuyer.setOnClickListener {
            // This will work once you create BuyerActivity
            val intent = Intent(this, loginactivity::class.java)
            startActivity(intent)
        }
    }
}
