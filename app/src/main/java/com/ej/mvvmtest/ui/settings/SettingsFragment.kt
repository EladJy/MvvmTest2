package com.ej.mvvmtest.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ej.mvvmtest.R
import com.ej.mvvmtest.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var _binding: FragmentSettingsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)
    }
}