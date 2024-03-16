package com.example.pass_manager.ui.screen.passwords_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pass_manager.R
import com.example.pass_manager.databinding.PasswordsFragmentBinding
import com.example.pass_manager.domain.model.Password
import com.example.pass_manager.ui.adapter.PasswordsListAdapter
import com.example.pass_manager.utils.LoadState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PasswordsFragment : Fragment(R.layout.passwords_fragment) {


    private var _binding : PasswordsFragmentBinding? = null
    val binding
        get() = _binding!!

    private val viewModel : PasswordsViewModel by activityViewModels()

    private val adapter = PasswordsListAdapter(object : PasswordsListAdapter.OnItemClickListener {
        override fun onUserItemClick(password: Password) {
            val direction = PasswordsFragmentDirections.actionPasswordsFragmentToEditFragment(password)
            findNavController().navigate(direction)
        }

    })

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
        viewModel.loadPasswordList()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {state ->
                    when (state.loadState) {
                        LoadState.LOADING -> setLoadingUi()
                        LoadState.SUCCESS -> setSuccessUi(state.passwords)
                        LoadState.NONEDATA -> setNoneDataUi()
                    }
                }
            }
        }
    }

    private fun setUi() {
        binding.apply {
            addButton.setOnClickListener {
                val direction = PasswordsFragmentDirections.actionPasswordsFragmentToEditFragment(null)
                findNavController().navigate(direction)
            }
            passwordList.adapter = adapter
            passwordList.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setLoadingUi() {
        binding.apply {
            passwordList.visibility = View.GONE
            textNoneData.visibility = View.GONE
            progress.visibility = View.VISIBLE
        }
    }

    private fun setSuccessUi(passwords : List<Password>) {
        binding.apply {
            progress.visibility = View.GONE
            passwordList.visibility = View.VISIBLE
            textNoneData.visibility = View.GONE
        }
        adapter.submitList(passwords)
    }

    private fun setNoneDataUi() {
        binding.apply {
            progress.visibility = View.GONE
            passwordList.visibility = View.GONE
            textNoneData.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}