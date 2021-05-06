package com.udacity.shoestore.fragments.ui_controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.fragments.viewmodels.GeneralViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        arguments?.let {
             val shoe = ShoeDetailFragmentArgs.fromBundle(requireArguments()).addedShoe!!
                    binding.nameEdit.setText(shoe.name)
                    binding.companyEdit.setText(shoe.company)
                    binding.sizeEdit.setText(shoe.size.toString())
                    binding.descriptionEdit.setText(shoe.description)
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.saveButton.setOnClickListener {
           val shoeToSend = Bundle()
            val shoe = Shoe(binding.nameEdit.text.toString(),binding.sizeEdit.text.toString().toDouble(),binding.companyEdit.text.toString(), binding.descriptionEdit.text.toString())
            shoeToSend.putParcelable(resources.getString(R.string.shoe),shoe)
            findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment, shoeToSend)

        }
        return binding.root
    }

}