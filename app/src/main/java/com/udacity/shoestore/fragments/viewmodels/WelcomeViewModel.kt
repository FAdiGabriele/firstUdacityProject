package com.udacity.shoestore.fragments.viewmodels

import androidx.lifecycle.ViewModel
import com.udacity.shoestore.WelcomeFragmentArgs

class WelcomeViewModel : ViewModel() {

    /**
     * function that take an [email] in input and extract tha first part  before the @
     */
    fun extractFirstPart(email : String) : String{
        return email.substring(0, email.indexOf("@"))
    }
}