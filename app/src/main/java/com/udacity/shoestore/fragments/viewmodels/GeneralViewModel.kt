package com.udacity.shoestore.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class GeneralViewModel : ViewModel() {

    private var asd = MutableLiveData<ArrayList<Shoe>>()

    val shoeList: LiveData<ArrayList<Shoe>>
    get() = asd


    fun addShoe(shoe: Shoe) {
        asd.value?.add(shoe)
    }

    init {
        asd.value = arrayListOf(
            Shoe("Delsie Stella", 39.toDouble(), "Clarks", ""),
            Shoe("Edell", 36.toDouble(), "Bandolino", ""),
            Shoe("Haven", 40.toDouble(), "Aerosoles", ""),
            Shoe("Jessica Bohemia Slide", 37.toDouble(), "Frye", ""),
            Shoe("Gelata Fresca", 40.toDouble(), "Clarks", ""),
            Shoe("Estee", 33.toDouble(), "Trotters", ""),
            Shoe("Simone", 39.toDouble(), "Wanted", ""))
    }
}