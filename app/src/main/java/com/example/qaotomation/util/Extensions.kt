package com.example.qaotomation.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.qaotomation.GlideApp
import com.example.qaotomation.R

@BindingAdapter("imageUrl")
fun loadImage(imageView: AppCompatImageView, url: String) {
    GlideApp.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.trendyol_logo)
        .centerCrop()
        .into(imageView)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun NavController.navigateUpOrFinish(activity: AppCompatActivity): Boolean {
    return if (navigateUp()) {
        true
    } else {
        activity.finish()
        true
    }
}