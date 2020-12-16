package ilyos.app.mvvm_hilt_example.base

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import ilyos.app.mvvm_hilt_example.R

/**
 * Developed by Ilyos
 */


abstract class BaseFragment(@LayoutRes private val layoutId: Int) : Fragment(layoutId) {

    protected lateinit var navController: NavController

    @IdRes
    var parentLayId: Int = R.id.container


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        parentLayId = viewModels<BaseViewModel>(ownerProducer = {requireActivity()}).value.parentLayoutId
        navController = Navigation.findNavController(requireView())
        initialize()
    }

    fun addFragment(
        fragment: BaseFragment,
        addBackStack: Boolean = true, @IdRes id: Int = parentLayId
    ) {
        requireActivity().supportFragmentManager.commit {
            if (addBackStack) addToBackStack(fragment.hashCode().toString())
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            add(id, fragment)
        }
    }

    fun replaceFragment(fragment: BaseFragment, @IdRes id: Int = parentLayId) {
        requireActivity().supportFragmentManager.commit {
            replace(id, fragment)
        }
    }

    fun finishFragment() {
        navController.popBackStack()
//        requireActivity().supportFragmentManager.popBackStackImmediate()
    }

    fun addFragmentWithAnim(fragment: BaseFragment, @IdRes id: Int = parentLayId) {
        hideKeyboard()
        requireActivity().supportFragmentManager.commit {
            setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
            add(id, fragment)
        }
    }

    fun message(message: String, isError: Boolean = true) {
        if (activity != null) {
            val snackbar = Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                "",
                Snackbar.LENGTH_LONG
            )
            snackbar.setActionTextColor(Color.WHITE)
            snackbar.setText(message)
            val sbView = snackbar.view
            var color: Int = R.color.colorRed
            if (!isError) {
                color = R.color.colorGreen
            }
            sbView.setBackgroundColor(ContextCompat.getColor(requireContext(), color))
            snackbar.show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard()
    }

    protected fun hideKeyboard() {
        val manager: InputMethodManager =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (view != null)
            manager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    abstract fun initialize()
}