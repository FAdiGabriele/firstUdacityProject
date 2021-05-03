package com.udacity.shoestore.viewmodels

import android.os.Bundle
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    /**
     * function that take inputs and put them in a bundle for passing data through safe args
     */
    fun prepareArguments(email : String, isNewUser : Boolean) : Bundle{
        val args = Bundle()
        args.putString("email", email)
        args.putBoolean("isNew", isNewUser)

       return args
    }

    /**
     * function that simulate an login
     */
    fun checkLogin(email: String, password : String) : Boolean{
        if(email != "fabioa.digabriele@gmail.com"){
            return false
        }
        if (password != "ILoveAndroid"){
            return false
        }
        return true
    }

    /**
     * function that check if the input for registration is valid
     */
    fun checkRegistration(email: String, password: String):Boolean{
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return false
        }
        if(password.length < 6){
          return false
        }
        return true
    }
}