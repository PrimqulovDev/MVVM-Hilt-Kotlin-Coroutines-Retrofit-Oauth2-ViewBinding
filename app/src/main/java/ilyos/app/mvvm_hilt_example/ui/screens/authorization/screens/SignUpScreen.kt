package ilyos.app.mvvm_hilt_example.ui.screens.authorization.screens

import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ilyos.app.mvvm_hilt_example.R
import ilyos.app.mvvm_hilt_example.base.BaseFragment
import ilyos.app.mvvm_hilt_example.databinding.ScreenSignUpBinding
import ilyos.app.mvvm_hilt_example.repo.api.auth.AuthServices
import ilyos.app.mvvm_hilt_example.repo.model.Register
import ilyos.app.mvvm_hilt_example.repo.model.Resource
import ilyos.app.mvvm_hilt_example.repo.model.Status
import ilyos.app.mvvm_hilt_example.ui.screens.authorization.AuthViewModel
import ilyos.app.mvvm_hilt_example.utils.extensions.isValidForEmail
import ilyos.app.mvvm_hilt_example.utils.extensions.isValidForPassword

@AndroidEntryPoint
class SignUpScreen : BaseFragment(R.layout.screen_sign_up) {

    private var _binding: ScreenSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun initialize() {
        _binding = ScreenSignUpBinding.bind(requireView())
        with(binding) {
            appBar.textTitle.text = ("Sign up")
            appBar.imageBack.setOnClickListener { finishFragment() }
            buttonCreateAccount.setOnClickListener {
                if (checkFields()) {
                    viewModel.createUser(
                        Register(
                            firstName = inputFirstName.text.toString(),
                            lastName = inputLastName.text.toString(),
                            email = inputEmail.text.toString(),
                            password = inputPassword.text.toString()
                        )
                    ).observe(viewLifecycleOwner, signUpObserver)
                }
            }
        }
    }

    private val signUpObserver = Observer<Resource<Register>> {

        when (it.status) {
            Status.SUCCESS -> {

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

            if (inputFirstName.text.toString().isEmpty()) {
                inputFirstName.errorMess = emptyError
                return false
            }

            if (inputLastName.text.toString().isEmpty()) {
                inputLastName.errorMess = emptyError
                return false
            }

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

            if (inputPassword.text.toString() != inputConfirmPassword.text.toString()) {
                inputConfirmPassword.errorMess = "Passwords don't match"
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

var EditText.errorMess: String
    get() = ""
    set(value) {
        requestFocus()
        error = value
    }