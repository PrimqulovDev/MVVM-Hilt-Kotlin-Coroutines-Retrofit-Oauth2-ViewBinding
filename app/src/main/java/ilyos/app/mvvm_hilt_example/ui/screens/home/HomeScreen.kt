package ilyos.app.mvvm_hilt_example.ui.screens.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ilyos.app.mvvm_hilt_example.R
import ilyos.app.mvvm_hilt_example.base.BaseFragment
import ilyos.app.mvvm_hilt_example.databinding.ScreenHomeBinding

@AndroidEntryPoint
class HomeScreen : BaseFragment(R.layout.screen_home) {

    private var _binding: ScreenHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun initialize() {
        _binding = ScreenHomeBinding.bind(requireView())

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}