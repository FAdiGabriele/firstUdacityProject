package com.udacity.shoestore.fragments.ui_controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.fragments.viewmodels.LoginViewModel


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //DataBinding
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        val viewModel = LoginViewModel()

        binding.loginButton.setOnClickListener {
            if (viewModel.checkLogin(
                    binding.emailEdittext.text.toString(),
                    binding.passwordEdittext.text.toString()
                )
            )
                goToWelcomeFragment(
                    viewModel.prepareArguments(
                        binding.emailEdittext.text.toString(),
                        false
                    )
                )
            else
                Toast.makeText(
                    requireContext(),
                    resources.getText(R.string.wrong_credential),
                    Toast.LENGTH_LONG
                ).show()

        }

        binding.registerButton.setOnClickListener {
            if (viewModel.checkRegistration(
                    binding.emailEdittext.text.toString(),
                    binding.passwordEdittext.text.toString()
                )
            )
                goToWelcomeFragment(
                    viewModel.prepareArguments(
                        binding.emailEdittext.text.toString(),
                        true
                    )
                )
            else
                Toast.makeText(
                    requireContext(),
                    resources.getText(R.string.invalid_credential),
                    Toast.LENGTH_LONG
                ).show()
        }

        return binding.root
    }

    /**
     * function that allow take in input the email and the status of the user
     * and do the navigation to Welcome Fragment
     */
    private fun goToWelcomeFragment(args: Bundle) {
        findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment, args)
    }

}