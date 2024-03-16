package com.example.pass_manager.ui.screen.passwords_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pass_manager.R
import com.example.pass_manager.databinding.PasswordsFragmentBinding

class PasswordsFragment : Fragment(R.layout.passwords_fragment) {


    private var _binding : PasswordsFragmentBinding? = null
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PasswordsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUi()
    }

    private fun setUi() {
        binding.apply {
            addButton.setOnClickListener {
                val direction = PasswordsFragmentDirections.actionPasswordsFragmentToEditFragment()
                findNavController().navigate(direction)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}