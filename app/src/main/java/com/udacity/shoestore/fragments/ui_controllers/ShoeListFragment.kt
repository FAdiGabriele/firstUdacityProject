package com.udacity.shoestore.fragments.ui_controllers

import android.app.ActionBar
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.SingleShoeElementBinding
import com.udacity.shoestore.fragments.viewmodels.GeneralViewModel
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {

    lateinit var binding: FragmentShoeListBinding

    //As suggested, I changed initialization of viewmodel with this direct modality
    private val viewModel: GeneralViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //DataBinding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }
        binding.lifecycleOwner = this


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            generateList(viewModel.shoeList.value!!)
        })

        // Added a callback for back navigation from this screen

        //The reason that explain because I added this code is explained in the row 113
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    returnToLogin()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }


    // Added a special instruction for navigating with action bar's menu

    //The reason that explain because I edit this method is explained in the row 113
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.loginFragment, android.R.id.home -> returnToLogin()
        }

        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun generateList(list: List<Shoe>) {
        for (shoe in list) {
            val shoeBinding = DataBindingUtil.inflate<SingleShoeElementBinding>(
                layoutInflater,
                R.layout.single_shoe_element,
                null,
                false
            )
            shoeBinding.nameValue.text = shoe.name
            shoeBinding.companyValue.text = shoe.company
            shoeBinding.sizeValue.text = shoe.size.toString()

            binding.listShoesContainer.addView(shoeBinding.root)

            /* As suggested, I deleted view separator on single cardView layout but
                I need a separator between card then I add a transparent view with 8dp height

                Obviously, I know that using a recyclerView we haven't this need */

            val separator = View(requireContext())
            separator.layoutParams = ViewGroup.LayoutParams(30, 30)
            //custom size in pixels and not in dp that is preferred
            binding.listShoesContainer.addView(separator)

        }
    }

    /* As suggested, in navigation I add the popUpTo in the action_loginFragment_to_welcomeFragment
        that allow to remove the loginFragment from the backstack when the user login, but this
        forbid me to return to loginFragment from this fragment thought backstack

        Then I add an action called action_shoeListFragment_to_loginFragment,
        that allow navigation from shoeList to Login skipping onBoarding screens
     */
    fun returnToLogin() {
        findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
    }


}