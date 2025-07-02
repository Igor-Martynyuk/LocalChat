package com.dev.martyniuk.local.chat.ui.view.auth.restore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dev.martyniuk.local.chat.ui.view.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAuthRestoreAccount : Fragment() {
    private val viewModel: ViewModelRestoreAccount by viewModels()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? =
        inflater.inflate(R.layout.fragment_auth_restore_account, group, false)

}