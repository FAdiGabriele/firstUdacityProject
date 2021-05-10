package com.udacity.shoestore.fragments.viewmodels

import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class GeneralViewModel : ViewModel() {

    /**
     * Backing propriety of ArrayList of Shoes that allow to observe but not modify the array list
     */
    private var _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
    get() = _shoeList

    /**
     * function that add a shoe to ArrayList
     */
    fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
    }

    /**
     * Here, I specify the initial values of the ArrayList
     */
    init {
        _shoeList.value = arrayListOf(
            Shoe("Delsie Stella", 39.toDouble(), "Clarks", ""),
            Shoe("Edell", 36.toDouble(), "Bandolino", ""),
            Shoe("Haven", 40.toDouble(), "Aerosoles", ""),
            Shoe("Jessica Bohemia Slide", 37.toDouble(), "Frye", ""),
            Shoe("Gelata Fresca", 40.toDouble(), "Clarks", ""),
            Shoe("Estee", 33.toDouble(), "Trotters", ""),
            Shoe("Simone", 39.toDouble(), "Wanted", ""))

    }

    /**
     * This function allow DataBinding to do a cast from double to String
     */
    fun transformerDoubleString(double : Double) : String{
        return double.toString()
    }

    /**
     * This function allow DataBinding to do a cast from String to double
     */
    @InverseMethod("transformerDoubleString")
    fun transformerStringDouble(string : String) : Double{
        if(string.isEmpty()) return 0.0
        return string.toDouble()
    }

}