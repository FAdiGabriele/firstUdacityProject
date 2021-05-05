package com.udacity.shoestore.fragments.viewmodels

import android.os.Bundle
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    /**
     * function that take in inputs [email] and if a user [isNewUser]
     * and put them in a bundle for passing data through safe args to welcome fragment
     */
    fun prepareArguments(email: String, isNewUser: Boolean): Bundle {
        val args = Bundle()
        args.putString("email", email)
        args.putBoolean("isNewUser", isNewUser)

        return args
    }

    /**
     * function that simulate a login
     * @return Boolean
     */
    fun checkLogin(email: String, password: String): Boolean {
        if (email != "pip@pip.it") {
            return false
        }
        if (password != "pip") {
            return false
        }
        return true
    }

    /**
     * function that check if
     * [email] meets the requirements of the RFC 822
     * [password] is at least 6 characters long
     * @return boolean
     */
    fun checkRegistration(email: String, password: String): Boolean {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }
        if (password.length < 6) {
            return false
        }
        return true
    }
}