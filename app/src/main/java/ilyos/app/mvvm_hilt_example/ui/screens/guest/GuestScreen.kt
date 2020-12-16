package ilyos.app.mvvm_hilt_example.ui.screens.guest

import androidx.navigation.fragment.findNavController
import ilyos.app.mvvm_hilt_example.utils.custom_views.CustomButton
import ilyos.app.mvvm_hilt_example.R
import ilyos.app.mvvm_hilt_example.base.BaseFragment
import ilyos.app.mvvm_hilt_example.ui.screens.authorization.screens.SplashScreen

class GuestScreen : BaseFragment(R.layout.screen_guest) {

    override fun initialize() {

        requireView().findViewById<CustomButton>(R.id.btnSignInUp)
            .setOnClickListener {
                findNavController().navigate(R.id.action_guestScreen_to_splashScreen2)
            }
    }

}