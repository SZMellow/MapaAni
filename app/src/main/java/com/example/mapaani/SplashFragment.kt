package com.example.mapaani

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.navigation.fragment.findNavController

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            if (!isAdded) return@postDelayed
            
            val sharedPref = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val isFirstRun = sharedPref.getBoolean("isFirstRun", true)
            val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

            Log.d("SplashFragment", "isFirstRun: $isFirstRun, isLoggedIn: $isLoggedIn")

            when {
                isFirstRun -> {
                    Log.d("SplashFragment", "Navigating to BoardingActivity")
                    val intent = Intent(requireContext(), BoardingActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                !isLoggedIn -> {
                    Log.d("SplashFragment", "Navigating to LoginActivity")
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                else -> {
                    Log.d("SplashFragment", "Navigating to Home via NavController")
                    try {
                        findNavController().navigate(R.id.action_splashFragment_to_nav_home)
                    } catch (e: Exception) {
                        Log.e("SplashFragment", "Navigation failed", e)
                    }
                }
            }
        }, 2000)

        return view
    }
}