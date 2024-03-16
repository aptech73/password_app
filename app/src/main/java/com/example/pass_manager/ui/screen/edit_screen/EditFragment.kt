package com.example.pass_manager.ui.screen.edit_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pass_manager.R
import com.example.pass_manager.databinding.EditFragmentBinding
import com.example.pass_manager.domain.model.Password
import com.example.pass_manager.utils.toEditable

class EditFragment : Fragment(R.layout.edit_fragment) {

    private var _binding : EditFragmentBinding? = null
    val binding
        get() = _binding!!

    private val viewModel : EditViewModel by activityViewModels()

    private val args : EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EditFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUi()

        val dataObject = args.password
        if (dataObject != null) {
            setEditUi(dataObject)
        }
    }

    private fun setUi() {
        binding.apply {
            backButtonCard.setOnClickListener {
                findNavController().navigateUp()
            }
            deleteButtonCard.visibility = View.GONE
            siteIcon.visibility = View.GONE
            saveButtonCard.setOnClickListener {
                viewModel.insertPasswordEntity(
                    Password(
                        id = 0,
                        siteName = siteTextField.editText?.text.toString(),
                        userName = userNameTextField.editText?.text.toString(),
                        password = passwordTextField.editText?.text.toString()
                    )
                )
                findNavController().navigateUp()
            }
        }
    }
    private fun setEditUi(password : Password) {
        binding.apply {
            deleteButtonCard.visibility = View.VISIBLE
            siteIcon.visibility = View.VISIBLE
            siteTextField.editText?.text = password.siteName.toEditable()
            userNameTextField.editText?.text = password.userName.toEditable()
            passwordTextField.editText?.text = password.password.toEditable()
            Glide.with(this@EditFragment)
                .load("https://besticon-demo.herokuapp.com/icon?url=${password.siteName}&size=64..64..64")
                .into(siteIcon)
            deleteButtonCard.setOnClickListener {
                viewModel.deletePasswordEntity(password)
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}