package com.dev.martyniuk.local.chat.ui.view.root.auth

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.dev.martyniuk.local.chat.core.common.extensions.isNull
import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherAuthNavigation
import com.dev.martyniuk.local.chat.ui.view.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActivityAuth : AppCompatActivity() {
    private val viewModel: ViewModelAuth by viewModels()

    private fun setupSystemInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        setupSystemInsets()
    }

    override fun onResume() {
        super.onResume()
        val controller = findNavController(R.id.container_nav_host)

        lifecycleScope.launch {
            viewModel.isAuthorized.observe(this@ActivityAuth) { if (it) finish() }

            viewModel.events.observe(this@ActivityAuth) {
                Log.d("temp_log", "we are here")
                if (it.isNull()) return@observe
                controller.navigate(
                    when (it) {
                        DispatcherAuthNavigation.Route.SignInWithGoogle -> R.id.action_auth_sign_in_to_google
                        DispatcherAuthNavigation.Route.SignInWithMicrosoft -> R.id.action_auth_sign_in_to_microsoft
                        DispatcherAuthNavigation.Route.SignInWithFacebook -> R.id.action_auth_sign_in_to_facebook
                        DispatcherAuthNavigation.Route.SignUpCredentials -> R.id.action_auth_sign_in_to_sign_up_credentials
                        DispatcherAuthNavigation.Route.SignUpPhoto -> R.id.action_auth_sign_up_credentials_to_photo
                        DispatcherAuthNavigation.Route.RestoreAccount -> R.id.action_auth_sign_in_to_restore_account
                        else -> throw UnsupportedOperationException("Unsupported navigation event received")
                    }
                )
            }
        }
    }
}