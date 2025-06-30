package com.dev.martyniuk.local.chat.ui.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dev.martyniuk.local.chat.ui.view.R
import com.dev.martyniuk.local.chat.ui.view.auth.ActivityAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityMain : AppCompatActivity() {
    private val viewModel: ViewModelMain by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.isAuthenticated.observe(this) {
            it?.let { value ->
                if (value.not()) launchAuthActivity()
                else setupContent()
            }
        }
    }

    private fun launchAuthActivity() = startActivity(
        Intent(this, ActivityAuth::class.java)
    )

    private fun setupContent() {
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }
}