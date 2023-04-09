package com.example.qaotomation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.qaotomation.data.UserRepository
import com.example.qaotomation.util.Constants

class SignupViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository(application.applicationContext)

    // region validation variables
    private var isSignUpSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    private var isEmailInvalid: MutableLiveData<Boolean> = MutableLiveData()
    private var isPasswordInvalid: MutableLiveData<Boolean> = MutableLiveData()
    private var passwordsDoesNotMatch: MutableLiveData<Boolean> = MutableLiveData()

    fun getIsSignupSuccessful(): LiveData<Boolean> = isSignUpSuccessful
    fun getIsEmailInvalid(): LiveData<Boolean> = isEmailInvalid
    fun getIsPasswordInvalid(): LiveData<Boolean> = isPasswordInvalid
    fun getPasswordsDoesNotMatch(): LiveData<Boolean> = passwordsDoesNotMatch
    // endregion

    fun signUp(email: String, password: String, reTypedPassword: String) {
        if (isInputValid(email, password, reTypedPassword)) {
            isSignUpSuccessful.value = userRepository.signUp(email, password)
        }
    }

    private fun isInputValid(email: String, password: String, reTypedPassword: String): Boolean {
        if (email.matches(Constants.EMAIL_PATTERN.toRegex()).not()) {
            isEmailInvalid.value = true
            return false
        }
        if (password.length < Constants.MIN_PASSWORD_LENGTH) {
            isPasswordInvalid.value = true
            return false
        }
        if ((password == reTypedPassword).not()) {
            passwordsDoesNotMatch.value = true
            return false
        }
        return true
    }
}