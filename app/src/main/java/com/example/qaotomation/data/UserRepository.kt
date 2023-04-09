package com.example.qaotomation.data

import android.content.Context
import android.content.SharedPreferences
import com.example.qaotomation.util.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserRepository(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREF_NAME, Constants.SHARED_PREF_MODE)
    private var userList = mutableListOf<User>()
    private val gson = Gson()
    private val editor = sharedPref.edit()

    fun login(email: String, password: String): Boolean {
        getUserList().forEach {
            if (it.username == email && it.password == password) {
                return true
            }
        }
        return false
    }

    private fun getUserList(): List<User> {
        val serializedList = sharedPref.getString(Constants.SHARED_PREF_USERS, "")
        if (serializedList.isNullOrEmpty().not()) {
            val type = object : TypeToken<List<User>>() {}.type
            userList = gson.fromJson(serializedList, type)
        }
        return userList
    }

    fun signUp(email: String, password: String): Boolean {
        getUserList().forEach {
            if (it.username == email) {
                return false
            }
        }
        userList.add(User(email, password))

        val listString = gson.toJson(userList)

        editor.putString(Constants.SHARED_PREF_USERS, listString)
        return editor.commit()
    }
}