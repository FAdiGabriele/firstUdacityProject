package com.udacity.shoestore.fragments.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GeneralViewModelProvider :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GeneralViewModel::class.java)) {
            return GeneralViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}