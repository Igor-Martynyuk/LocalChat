package com.dev.martyniuk.local.chat.ui.view.auth

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
import com.dev.martyniuk.local.chat.ui.view.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActivityAuth : AppCompatActivity() {
    private val viewModel: ViewModelAuth by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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
                        EventDispatcherAuth.NavigationCommand.SignInWithGoogle -> R.id.action_auth_sign_in_to_google
                        EventDispatcherAuth.NavigationCommand.SignInWithMicrosoft -> R.id.action_auth_sign_in_to_microsoft
                        EventDispatcherAuth.NavigationCommand.SignInWithFacebook -> R.id.action_auth_sign_in_to_facebook
                        EventDispatcherAuth.NavigationCommand.SignUpCredentials -> R.id.action_auth_sign_in_to_sign_up_credentials
                        EventDispatcherAuth.NavigationCommand.SignUpPhoto -> R.id.action_auth_sign_up_credentials_to_photo
                        EventDispatcherAuth.NavigationCommand.RestoreAccount -> R.id.action_auth_sign_in_to_restore_account
                        else -> throw UnsupportedOperationException("Unsupported navigation event received")
                    }
                )
            }
        }
    }
}