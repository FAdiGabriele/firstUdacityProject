package com.udacity.shoestore.fragments.ui_controllers

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.SingleShoeElementBinding
import com.udacity.shoestore.fragments.viewmodels.GeneralViewModel
import com.udacity.shoestore.fragments.viewmodels.GeneralViewModelProvider
import com.udacity.shoestore.models.Shoe
import kotlin.collections.ArrayList


class ShoeListFragment : Fragment() {

    lateinit var binding: FragmentShoeListBinding
    lateinit var viewModel: GeneralViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //DataBinding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        val mainActivity = requireActivity() as MainActivity
        binding.generalViewModel = mainActivity.viewModel

        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = binding.generalViewModel!!

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
                generateList(viewModel.shoeList.value!!)
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun generateList(list: ArrayList<Shoe>) {
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

            shoeBinding.itemCard.setOnClickListener{
                val shoeToSend = Bundle()
                shoeToSend.putParcelable(resources.getString(R.string.shoe),shoe)
                findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment, shoeToSend)
            }
            binding.listShoesContainer.addView(shoeBinding.root)
        }
    }
}