package ilyos.app.mvvm_hilt_example.ui.screens.authorization.screens

import ilyos.app.mvvm_hilt_example.utils.custom_views.CustomButton
import ilyos.app.mvvm_hilt_example.R
import ilyos.app.mvvm_hilt_example.base.BaseFragment

class SplashScreen : BaseFragment(R.layout.screen_splash) {

    override fun initialize() {
        requireView().findViewById<CustomButton>(R.id.buttonSignIn).setOnClickListener {
            navController.navigate(R.id.signInScreen)
        }
        requireView().findViewById<CustomButton>(R.id.buttonSignUp).setOnClickListener {
            navController.navigate(R.id.signUpScreen)
        }
    }



}