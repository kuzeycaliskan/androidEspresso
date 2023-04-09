package com.example.qaotomation.viewstate

import com.example.qaotomation.data.BoutiqueItem

class BoutiqueItemViewState(
    private val boutiqueItem: BoutiqueItem
) {
    fun getImageUrl(): String = boutiqueItem.imageUrl

    fun getTitle(): String = boutiqueItem.title

    fun getCaption(): String = boutiqueItem.caption
}