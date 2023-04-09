package com.example.qaotomation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qaotomation.databinding.ListItemBinding
import com.example.qaotomation.viewstate.BoutiqueItemViewState

class BoutiqueRecyclerViewAdapter:
    RecyclerView.Adapter<BoutiqueRecyclerViewAdapter.BoutiqueViewHolder>() {
    private val boutiqueList: MutableList<BoutiqueItemViewState> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoutiqueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater)

        return BoutiqueViewHolder(binding)
    }

    fun setList(itemList: List<BoutiqueItemViewState>) {
        this.boutiqueList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BoutiqueViewHolder, position: Int) {
        val currentBoutique = boutiqueList[position]
        holder.binding.viewState = currentBoutique
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return boutiqueList.size
    }

    class BoutiqueViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)
}