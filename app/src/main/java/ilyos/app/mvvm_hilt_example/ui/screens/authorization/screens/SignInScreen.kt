package ilyos.app.mvvm_hilt_example.ui.screens.authorization.screens

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ilyos.app.mvvm_hilt_example.R
import ilyos.app.mvvm_hilt_example.base.BaseFragment
import ilyos.app.mvvm_hilt_example.databinding.ScreenSignInBinding
import ilyos.app.mvvm_hilt_example.repo.api.oauth2.OauthToken
import ilyos.app.mvvm_hilt_example.repo.model.Resource
import ilyos.app.mvvm_hilt_example.repo.model.Status
import ilyos.app.mvvm_hilt_example.ui.screens.authorization.AuthViewModel
import ilyos.app.mvvm_hilt_example.utils.extensions.isValidForEmail
import ilyos.app.mvvm_hilt_example.utils.extensions.isValidForPassword

@AndroidEntryPoint
class SignInScreen : BaseFragment(R.layout.screen_sign_in) {

    private var _binding: ScreenSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun initialize() {
        _binding = ScreenSignInBinding.bind(requireView())

        binding.apply {
            buttonGuest.setOnClickListener { navController.navigate(R.id.guestScreen) }
            buttonForgotPassword.setOnClickListener { navController.navigate(R.id.resetPasswordScreen) }
            imageBack.setOnClickListener { finishFragment() }
            buttonLogin.setOnClickListener {
                if (checkFields()) {
                    val username = binding.inputEmail.text.toString()
                    val password = binding.inputPassword.text.toString()
                    viewModel.getAuth(username, password).observe(viewLifecycleOwner, oauthTokenObserver)
                }
            }
        }


    }

    private val oauthTokenObserver = Observer<Resource<OauthToken>> {
        when (it.status) {
            Status.SUCCESS -> {
                viewModel.sharedManager.saveOauthToken(it.data!!)
            }
            Status.ERROR -> {
                message(it.message!!)
            }
            Status.LOADING -> {

            }
        }
    }

    private fun checkFields(): Boolean {
        val emptyError = "Field is required"
        with(binding) {
            if (inputEmail.text.toString().isEmpty()) {
                inputEmail.errorMess = emptyError
                return false
            }

            if (!inputEmail.isValidForEmail()) {
                inputEmail.errorMess = getString(R.string.emailValidationError)
                return false
            }

            if (inputPassword.text.toString().isEmpty()) {
                inputPassword.errorMess = emptyError
                return false
            }

            if (!inputPassword.isValidForPassword()) {
                inputPassword.errorMess = getString(R.string.passwordValidationError)
                return false
            }
        }
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}