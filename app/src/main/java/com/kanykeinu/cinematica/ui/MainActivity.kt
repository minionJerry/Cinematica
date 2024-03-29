package com.kanykeinu.cinematica.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.kanykeinu.cinematica.R
import androidx.navigation.Navigation


class MainActivity : AppCompatActivity() {

    private var currentController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.kanykeinu.cinematica.R.layout.activity_main)
        currentController = findNavController(R.id.container)
        NavigationUI.setupActionBarWithNavController(this, currentController!!)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.movie_list_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

    override fun onBackPressed() {
        currentController
            ?.let { if (it.popBackStack().not()) finish() }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentController!!.navigateUp()
    }
}
