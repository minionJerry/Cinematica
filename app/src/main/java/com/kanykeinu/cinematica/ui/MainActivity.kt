package com.kanykeinu.cinematica.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.kanykeinu.cinematica.R

class MainActivity : AppCompatActivity() {

    private var currentController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentController = findNavController(R.id.container)
    }

    override fun onBackPressed() {
        currentController
            ?.let { if (it.popBackStack().not()) finish() }
    }
}
