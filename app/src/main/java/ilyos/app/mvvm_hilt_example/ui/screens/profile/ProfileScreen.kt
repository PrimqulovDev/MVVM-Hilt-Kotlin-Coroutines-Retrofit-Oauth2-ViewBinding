package ilyos.app.mvvm_hilt_example.ui.screens.profile

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ilyos.app.mvvm_hilt_example.R
import ilyos.app.mvvm_hilt_example.base.BaseFragment
import ilyos.app.mvvm_hilt_example.databinding.ScreenProfileBinding
import javax.inject.Inject

@AndroidEntryPoint
class ProfileScreen : BaseFragment(R.layout.screen_profile) {

    private var _binding: ScreenProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun initialize() {
        _binding = ScreenProfileBinding.bind(requireView())


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}