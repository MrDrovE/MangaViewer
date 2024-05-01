package com.example.mangaviewer

import java.io.File
import android.os.Bundle
import android.os.storage.StorageManager
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mangaviewer.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val button: Button = findViewById(R.id.button)
        val text:TextView = findViewById(R.id.textView)
        button.setOnClickListener(){
            lifecycleScope.launch { val value = searchPDF()
                text.text = value.toString()
            }
        }
    }
    private fun sdas(){
        lifecycleScope.launch { val p=searchPDF() }

    }
    private suspend fun searchPDF(): List<Any>{
        return withContext(Dispatchers.IO){
            val files = filesDir.listFiles()
            files.filter{it.canRead()}
        }
    }
}