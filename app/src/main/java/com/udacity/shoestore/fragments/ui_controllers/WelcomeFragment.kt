package com.udacity.shoestore.fragments.ui_controllers

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.WelcomeFragmentArgs
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.fragments.viewmodels.WelcomeViewModel

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //DataBinding
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false
        )
        setHasOptionsMenu(true)

        val args = WelcomeFragmentArgs.fromBundle(requireArguments())

        binding.usernameTextview.text = viewModel.extractFirstPart(args.email)

        binding.welcomeMessageTextview.text =
            if (args.newUser) resources.getText(R.string.welcome)
            else resources.getText(R.string.welcome_back)

        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_instructionFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController()) || super.onOptionsItemSelected(item)

    }
}