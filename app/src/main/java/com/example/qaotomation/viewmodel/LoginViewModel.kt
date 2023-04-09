package com.example.qaotomation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.qaotomation.data.UserRepository
import com.example.qaotomation.util.Constants

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository(application.applicationContext)

    private var isCredentialsCorrect: MutableLiveData<Boolean> = MutableLiveData()
    private var isEmailInvalid: MutableLiveData<Boolean> = MutableLiveData()

    fun getIsCredentialsCorrect(): LiveData<Boolean> = isCredentialsCorrect
    fun getIsEmailInvalid(): LiveData<Boolean> = isEmailInvalid

    fun login(email: String, password: String) {
        if (isInputValid(email)) {
            isCredentialsCorrect.value = userRepository.login(email, password)
        }
    }

    private fun isInputValid(email: String): Boolean {
        if (email.matches(Constants.EMAIL_PATTERN.toRegex()).not()) {
            isEmailInvalid.value = true
            return false
        }
        return true
    }
}