package ilyos.app.mvvm_hilt_example.ui.screens.faq

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ilyos.app.mvvm_hilt_example.R
import ilyos.app.mvvm_hilt_example.base.BaseFragment
import ilyos.app.mvvm_hilt_example.databinding.ScreenFaqBinding

@AndroidEntryPoint
class FAQScreen : BaseFragment(R.layout.screen_faq) {

    private var _binding: ScreenFaqBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FAQViewModel by viewModels()

    override fun initialize() {
        _binding = ScreenFaqBinding.bind(requireView())

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}