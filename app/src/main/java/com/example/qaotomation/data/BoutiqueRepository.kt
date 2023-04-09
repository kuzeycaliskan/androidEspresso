package com.example.qaotomation.data

import com.example.qaotomation.viewstate.BoutiqueItemViewState

class BoutiqueRepository {
    private val boutiques = listOf(
        BoutiqueItem(
            imageUrl = "https://img-trendyol.mncdn.com//Assets/Stage/ProductImages/oa/CampaignVisual/OriginalBoutiqueImages/6006651/650c748fa6_1.jpg",
            caption = "Garaj Sale",
            title = "Trendyol"
        ),
        BoutiqueItem(
            imageUrl = "https://img-trendyol.mncdn.com//Assets/Stage/ProductImages/oa/CampaignVisual/OriginalBoutiqueImages/6006653/eafbdadb63_10.jpg",
            caption = "Büyük Sezon İndirimi",
            title = "Trendyol"
        ),
        BoutiqueItem(
            imageUrl = "https://img-trendyol.mncdn.com//Assets/Stage/ProductImages/oa/CampaignVisual/OriginalBoutiqueImages/6006816/a65656ca0b_1.jpg",
            caption = "Saat Şöleni",
            title = "Trendyol"
        ),
        BoutiqueItem(
            imageUrl = "https://img-trendyol.mncdn.com//Assets/Stage/ProductImages/oa/CampaignVisual/OriginalBoutiqueImages/6006321/63d077d770_0.jpg",
            caption = "Chiccy",
            title = "Trendyol"
        ),
        BoutiqueItem(
            imageUrl = "https://img-trendyol.mncdn.com//Assets/Stage/ProductImages/oa/CampaignVisual/OriginalBoutiqueImages/6006661/59b971379e_1.jpg",
            caption = "Tek Kalanlar",
            title = "Trendyol"
        ),
        BoutiqueItem(
            imageUrl = "https://img-trendyol.mncdn.com//Assets/Stage/ProductImages/oa/CampaignVisual/OriginalBoutiqueImages/6006649/9ddf865940_1.jpg",
            caption = "İndirim Festivali",
            title = "Trendyol"
        ),
        BoutiqueItem(
            imageUrl = "https://img-trendyol.mncdn.com//Assets/Stage/ProductImages/oa/CampaignVisual/OriginalBoutiqueImages/6007819/8e66e975ce_0.jpg",
            caption = "Bi Tshirt Bi Jean Bi Sneaker",
            title = "Trendyol"
        ),
        BoutiqueItem(
            imageUrl = "https://img-trendyol.mncdn.com//Assets/Stage/ProductImages/oa/CampaignVisual/OriginalBoutiqueImages/6006318/e1745d268f_0.jpg",
            caption = "Alaçatı",
            title = "Trendyol"
        ),
        BoutiqueItem(
            imageUrl = "https://img-trendyol.mncdn.com//Assets/Stage/ProductImages/oa/CampaignVisual/OriginalBoutiqueImages/6006863/a10e7d8520_0.jpg",
            caption = "Dilvin",
            title = "Trendyol"
        )
    )

    private val boutiqueItemViewStateList = mutableListOf<BoutiqueItemViewState>()

    fun getBoutiquesViewState(): List<BoutiqueItemViewState> {
        boutiques.forEach {
            boutiqueItemViewStateList.add(BoutiqueItemViewState(it))
        }
        return boutiqueItemViewStateList
    }
}