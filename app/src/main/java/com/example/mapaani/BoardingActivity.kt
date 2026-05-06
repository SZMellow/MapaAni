package com.example.mapaani

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class BoardingActivity : AppCompatActivity() {

    private lateinit var boardingAdapter: BoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_boarding)
        
        // Use a safe check for the root view ID
        val rootView = findViewById<android.view.View>(android.R.id.content)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupBoardingItems()
    }

    private fun setupBoardingItems() {
        val boardingItems = listOf(
            BoardingItem(
                "Order Vegetables",
                "connects you directly to the heart of local agriculture, delivering fresh, seasonal harvests straight to your door.",
                R.drawable.potatoes,
                R.drawable.ic_dish
            ),
            BoardingItem(
                "Easy Payment",
                "You could pay from online banks, to cash on delivery.",
                R.drawable.potatoes,
                R.drawable.ic_list
            ),
            BoardingItem(
                "Fast Delivery",
                "MapaAni will connect you with drivers quickly. Delivering from point A to point B",
                R.drawable.potatoes,
                R.drawable.ic_home
            )
        )

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        boardingAdapter = BoardingAdapter(
            boardingItems,
            onNextClicked = { position ->
                if (position < boardingItems.size - 1) {
                    viewPager.currentItem = position + 1
                } else {
                    navigateToLogin()
                }
            },
            onSkipClicked = {
                navigateToLogin()
            }
        )
        viewPager.adapter = boardingAdapter
    }

    private fun navigateToLogin() {
        val sharedPref = getSharedPreferences("app_prefs", android.content.Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("isFirstRun", false).apply()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
