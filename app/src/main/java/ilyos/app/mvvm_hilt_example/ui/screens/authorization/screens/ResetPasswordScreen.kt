package ilyos.app.mvvm_hilt_example.ui.screens.authorization.screens

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ilyos.app.mvvm_hilt_example.base.BaseFragment
import ilyos.app.mvvm_hilt_example.ui.screens.authorization.AuthViewModel
import ilyos.app.mvvm_hilt_example.R
import ilyos.app.mvvm_hilt_example.utils.extensions.isValidForEmail
import ilyos.app.mvvm_hilt_example.utils.extensions.isEmpty
import ilyos.app.mvvm_hilt_example.databinding.ScreenResetPasswordBinding
import ilyos.app.mvvm_hilt_example.repo.model.Resource
import ilyos.app.mvvm_hilt_example.repo.model.Status
import okhttp3.ResponseBody
import javax.inject.Inject

@AndroidEntryPoint
class ResetPasswordScreen : BaseFragment(R.layout.screen_reset_password) {

    private var _binding: ScreenResetPasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun initialize() {
        _binding = ScreenResetPasswordBinding.bind(requireView())
        binding.appBar.textTitle.text = ("Reset Password")
        binding.appBar.imageBack.setOnClickListener { finishFragment() }
        binding.buttonNext.setOnClickListener {
            with(binding.editTextMail) {
                when {
                    isEmpty() -> {
                        errorMess = ("Field is required")
                    }
                    !isValidForEmail() -> {
                        errorMess = ("Email should be like example@company.com")
                    }
                    else -> {
                        viewModel.forgotPassword(text.toString()).observe(viewLifecycleOwner, forgotPasswordObserver)
                    }
                }
            }
        }
    }

    private val forgotPasswordObserver = Observer<Resource<ResponseBody>> {

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}