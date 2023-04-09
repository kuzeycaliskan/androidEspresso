package com.example.qaotomation.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.qaotomation.R
import com.example.qaotomation.databinding.MainFragmentBinding
import com.example.qaotomation.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: MainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)

        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        binding.buttonSignup.setOnClickListener {
            findNavController().navigate(R.id.signupFragment)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
}