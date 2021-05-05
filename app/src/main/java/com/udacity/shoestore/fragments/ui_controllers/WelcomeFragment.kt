package com.udacity.shoestore.fragments.ui_controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.fragments.viewmodels.WelcomeViewModel

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //DataBinding
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false
        )

        val viewModel = WelcomeViewModel()

        val args = WelcomeFragmentArgs.fromBundle(requireArguments())

        binding.usernameTextview.text = viewModel.extractFirstPart(args.email)

        binding.welcomeMessageTextview.text =
            if (args.isNewUser) resources.getText(R.string.welcome) else resources.getText(R.string.welcome_back)

        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_instructionFragment)
        }

        return binding.root
    }

}