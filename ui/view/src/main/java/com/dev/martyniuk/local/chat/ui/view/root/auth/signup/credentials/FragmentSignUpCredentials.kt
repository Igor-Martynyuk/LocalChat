package com.dev.martyniuk.local.chat.ui.view.root.auth.signup.credentials

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.R
import com.dev.martyniuk.local.chat.ui.view.root.auth.EventDispatcherAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSignUpCredentials : Fragment() {
    private val viewModel: ViewModelSignUpCredentials by viewModels()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? =
        inflater.inflate(R.layout.fragment_auth_sign_up_credentials, group, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_navigate_setup_photo).setOnClickListener {
            viewModel.eventDispatcher.send(EventDispatcherAuth.NavigationCommand.SignUpPhoto)
        }
    }
}