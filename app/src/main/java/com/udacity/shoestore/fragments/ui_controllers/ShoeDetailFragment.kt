package com.udacity.shoestore.fragments.ui_controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.fragments.viewmodels.GeneralViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    lateinit var viewModel: GeneralViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        val mainActivity = requireActivity() as MainActivity
        viewModel = mainActivity.viewModel
        binding.generalViewModel = viewModel

        binding.saveButton.setOnClickListener {
            viewModel.addShoe(binding.shoeToAdd!!)
            findNavController().navigateUp()
        }

        binding.cancelButton.setOnClickListener {
            findNavController()
        }


        binding.shoeToAdd= Shoe("",0.0,"","")
        return binding.root
    }

}