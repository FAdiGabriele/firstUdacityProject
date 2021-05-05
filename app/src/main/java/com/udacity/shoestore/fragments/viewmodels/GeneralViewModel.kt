package com.udacity.shoestore.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class GeneralViewModel : ViewModel() {

    private val __shoeList = arrayListOf(
        Shoe("Delsie Stella", 39.toDouble(), "Clarks", "" ),
        Shoe("Edell", 36.toDouble(), "Bandolino", "" ),
        Shoe("Haven", 40.toDouble(), "Aerosoles", "" ),
        Shoe("Jessica Bohemia Slide", 37.toDouble(), "Frye", "" ),
        Shoe("Gelata Fresca", 40.toDouble(), "Clarks", "" ),
        Shoe("Estee", 33.toDouble(), "Trotters", "" ),
        Shoe("Simone", 39.toDouble(), "Wanted", "" )
    )
    private var _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList : LiveData<ArrayList<Shoe>>
        get() = _shoeList


    fun addShoe(shoe : Shoe){
        __shoeList.add(shoe)
        _shoeList.value = __shoeList
    }

    fun getShoe(index : Int) : Shoe? {
        return shoeList.value?.get(index)
    }

}