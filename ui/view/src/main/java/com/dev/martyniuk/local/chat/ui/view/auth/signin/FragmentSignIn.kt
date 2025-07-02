package com.dev.martyniuk.local.chat.ui.view.auth.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignIn : Fragment() {
    private val viewModel: ViewModelSignIn by viewModels()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? =
        inflater.inflate(R.layout.fragment_auth_sign_in, group, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_sign_in).setOnClickListener {
            viewModel.onSignInCommand("email", "password")
        }

        view.findViewById<Button>(R.id.btn_sign_in_with_google).setOnClickListener {
            viewModel.onSignInWithGoogleCommand()
        }

        view.findViewById<Button>(R.id.btn_sign_in_with_microsoft).setOnClickListener {
            viewModel.onSignInWithMicrosoft()
        }

        view.findViewById<Button>(R.id.btn_sign_in_with_facebook).setOnClickListener {
            viewModel.onSignInWithFacebook()
        }

        view.findViewById<Button>(R.id.btn_sign_up).setOnClickListener {
            viewModel.onSignUpCommand()
        }

        view.findViewById<Button>(R.id.btn_restore_account).setOnClickListener {
            viewModel.onRestoreAccountCommand()
        }
    }
}