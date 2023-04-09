package com.example.qaotomation.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.qaotomation.databinding.LoginFragmentBinding
import com.example.qaotomation.util.hideKeyboard
import com.example.qaotomation.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)

        binding.buttonLoginUser.setOnClickListener {
            hideKeyboard()
            viewModel.login(
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        with(viewModel) {
            getIsCredentialsCorrect().observe(viewLifecycleOwner, {
                if (it == true) {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToListFragment())
                } else {
                    showAlertDialog()
                }
            })
            getIsEmailInvalid().observe(viewLifecycleOwner, {
                displayEmailInvalidWarning()
            })
        }
    }

    private fun displayEmailInvalidWarning() {
        Snackbar.make(
            binding.root,
            "Girdiğiniz email geçersizdir.",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Hata")
            .setMessage("Email veya şifre yanlış.")
            .setPositiveButton("OK", null)
            .show()
    }
}