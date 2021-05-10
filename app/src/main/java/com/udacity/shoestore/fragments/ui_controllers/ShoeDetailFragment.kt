package com.udacity.shoestore.fragments.ui_controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.fragments.viewmodels.GeneralViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    //As suggested, I changed initialization of viewmodel with this direct modality
    private val viewModel: GeneralViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        binding.generalViewModel = viewModel

        binding.saveButton.setOnClickListener {
            if (isValidShoe(
                    binding.nameEdit.text.toString(),
                    binding.sizeEdit.text.toString().toDouble(),
                    binding.companyEdit.text.toString(),
                    binding.descriptionEdit.text.toString()
                )
            ) {
                viewModel.addShoe(binding.shoeToAdd!!)
                findNavController().navigateUp()
            }
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }


    /**
     * As suggested, I add a function that validate the input for create a new shoe
     */
    private fun isValidShoe(
        name: String,
        size: Double,
        company: String,
        description: String
    ): Boolean {
        return when {
            name.isBlank() -> {
                Toast.makeText(
                    requireContext(),
                    resources.getText(R.string.name_error_input),
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
            size < 0.0 -> {
                Toast.makeText(
                    requireContext(),
                    resources.getText(R.string.size_error_input),
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
            company.isBlank() -> {
                Toast.makeText(
                    requireContext(),
                    resources.getText(R.string.company_error_input),
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
            description.isBlank() -> {
                Toast.makeText(
                    requireContext(),
                    resources.getText(R.string.description_error_input),
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
            else -> true
        }

    }
}