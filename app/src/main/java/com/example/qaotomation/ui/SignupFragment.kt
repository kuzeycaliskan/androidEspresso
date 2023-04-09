package com.example.qaotomation.ui

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.qaotomation.databinding.SignupFragmentBinding
import com.example.qaotomation.util.hideKeyboard
import com.example.qaotomation.viewmodel.SignupViewModel
import com.google.android.material.snackbar.Snackbar

class SignupFragment : Fragment() {
    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: SignupFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignupFragmentBinding.inflate(inflater, container, false)

        binding.buttonSignupUser.setOnClickListener {
            hideKeyboard()
            viewModel.signUp(
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString(),
                binding.editTextRePassword.text.toString()
            )
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignupViewModel::class.java)

        with(viewModel) {
            getIsSignupSuccessful().observe(viewLifecycleOwner, {
                if (it == true) {
                    // sign up successful, navigate back.
                    showSuccessfulRegisterDialog()
                } else {
                    displayAlreadyExistingUserWarning()
                }
            })
            getIsEmailInvalid().observe(viewLifecycleOwner, {
                displayEmailInvalidWarning()
            })
            getIsPasswordInvalid().observe(viewLifecycleOwner, {
                displayPasswordInvalidWarning()
            })
            getPasswordsDoesNotMatch().observe(viewLifecycleOwner, {
                displayPasswordsDoesNotMatchWarning()
            })
        }
    }

    private fun displayEmailInvalidWarning() {
        Snackbar.make(
            binding.root,
            "Girdiğiniz email geçersizdir. Lütfen geçerli bir email girin.",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun displayPasswordInvalidWarning() {
        Snackbar.make(
            binding.root,
            "En az sekiz karakterden oluşan bir şifre belirleyin.",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun displayPasswordsDoesNotMatchWarning() {
        Snackbar.make(
            binding.root,
            "Şifreler eşleşmiyor, lütfen tekrar kontrol edin.",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun displayAlreadyExistingUserWarning() {
        Snackbar.make(
            binding.root,
            "Bu mail bir kullanıcı tarafından kullanılıyor.",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun showSuccessfulRegisterDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Kayıt Ol")
            .setMessage("Başarılı şekilde kayıt oldun.")
            .setPositiveButton("OK") { dialog, which ->
                findNavController().popBackStack()
            }
            .setCancelable(false)
            .show()
    }
}