package com.example.qaotomation.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.qaotomation.R
import com.example.qaotomation.adapter.BoutiqueRecyclerViewAdapter
import com.example.qaotomation.databinding.ItemListingFragmentBinding
import com.example.qaotomation.viewmodel.ItemListingViewModel
import com.example.qaotomation.viewstate.BoutiqueItemViewState
import java.util.*

class ItemListingFragment : Fragment() {

    private lateinit var viewModel: ItemListingViewModel
    private lateinit var binding: ItemListingFragmentBinding
    private var adapter: BoutiqueRecyclerViewAdapter = BoutiqueRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemListingFragmentBinding.inflate(inflater, container, false)

        binding.overlayDatePicker.setOnClickListener {
            displayDatePickerDialog()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showLogoutDialog()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemListingViewModel::class.java)

        with(viewModel) {

            getBoutiqeItemViewStates().observe(viewLifecycleOwner, {
                setupRecyclerView(it)
            })
            getDateLiveData().observe(viewLifecycleOwner, {
                binding.editTextDatePicker.setText(it)
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    private fun displayDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(requireContext(), { _, currentYear, monthOfYear, dayOfMonth ->
                viewModel.formatDate(dayOfMonth, monthOfYear, currentYear)
            }, year, month, day)

        datePickerDialog.show()
    }

    private fun setupRecyclerView(viewStateList: List<BoutiqueItemViewState>) {
        adapter.setList(viewStateList)
        binding.recyclerViewItems.adapter = adapter
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).supportActionBar?.hide()

        binding.toolbarListing.inflateMenu(R.menu.listing_menu)
        binding.toolbarListing.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_logout -> {
                    showLogoutDialog()
                    true
                }
                else -> super.onOptionsItemSelected(it)
            }
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Çıkış Yap")
            .setMessage("Çıkış yapmak istediğine emin misin?")
            .setPositiveButton("Çık") { dialog, which ->
                findNavController().navigate(ItemListingFragmentDirections.listingToMainFragment())
                (activity as AppCompatActivity).supportActionBar?.show()
            }
            .setNegativeButton("Hayır", null)
            .show()
    }
}