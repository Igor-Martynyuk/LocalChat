package com.dev.martyniuk.local.chat.ui.view.root.auth

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.dev.martyniuk.local.chat.core.layer.ui.event.dispatcher.DispatcherNavigationAuth
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

    private fun setupObservers() = findViewById<View>(R.id.container_nav_host).post {
        val controller = findNavController(R.id.container_nav_host)

        lifecycleScope.launch {
            viewModel.isAuthorized.observe(this@ActivityAuth) { if (it) finish() }
            viewModel.events.observe(this@ActivityAuth) { event ->
                event.consume {
                    when (it) {
                        DispatcherNavigationAuth.Route.SignIn -> controller.popBackStack(R.id.destination_auth_sign_in, false)
                        DispatcherNavigationAuth.Route.SignInWithGoogle -> controller.navigate(R.id.action_auth_sign_in_to_google)
                        DispatcherNavigationAuth.Route.SignInWithMicrosoft -> controller.navigate(R.id.action_auth_sign_in_to_microsoft)
                        DispatcherNavigationAuth.Route.SignInWithFacebook -> controller.navigate(R.id.action_auth_sign_in_to_facebook)
                        DispatcherNavigationAuth.Route.SignUp -> {
                            controller.navigate(R.id.action_auth_sign_in_to_sign_up)
                            controller.navigate(R.id.action_auth_sign_up_credentials_to_photo)
                        }
                        DispatcherNavigationAuth.Route.SignUpPhoto -> controller.navigate(R.id.action_auth_sign_up_credentials_to_photo)
                        DispatcherNavigationAuth.Route.RestoreAccount -> controller.navigate(R.id.action_auth_sign_in_to_restore_account)
                        else -> throw UnsupportedOperationException("Unsupported navigation event received")
                    }
                }
            }
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
        setupObservers()
    }
}