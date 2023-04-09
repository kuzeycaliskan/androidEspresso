package com.example.qaotomation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qaotomation.data.BoutiqueRepository
import com.example.qaotomation.viewstate.BoutiqueItemViewState

class ItemListingViewModel : ViewModel() {
    private val boutiqueRepository = BoutiqueRepository()
    private val boutiqueViewStateMutableLiveData: MutableLiveData<List<BoutiqueItemViewState>> =
        MutableLiveData()
    private val dateLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        getBoutiques()
    }

    fun getBoutiqeItemViewStates(): LiveData<List<BoutiqueItemViewState>> = boutiqueViewStateMutableLiveData
    fun getDateLiveData(): LiveData<String> = dateLiveData

    private fun getBoutiques() {
        boutiqueViewStateMutableLiveData.value = boutiqueRepository.getBoutiquesViewState()
    }

    fun formatDate(dayOfMonth: Int, monthOfYear: Int, year: Int) {
        var stringDay: String
        var stringMonth: String
        var stringYear: String

        stringDay = if (dayOfMonth < 10) "0$dayOfMonth"
        else dayOfMonth.toString()

        stringMonth = if (monthOfYear < 9) "0${monthOfYear+1}"
        else (monthOfYear + 1).toString()

        stringYear = year.toString()

        dateLiveData.value = "$stringDay/$stringMonth/$stringYear"
    }
}