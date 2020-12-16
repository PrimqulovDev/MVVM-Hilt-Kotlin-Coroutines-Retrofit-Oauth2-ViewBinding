package ilyos.app.mvvm_hilt_example.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ilyos.app.mvvm_hilt_example.R
import ilyos.app.mvvm_hilt_example.base.exitVariant
import ilyos.app.mvvm_hilt_example.base.finishFragment
import ilyos.app.mvvm_hilt_example.utils.DataStore
import ilyos.app.mvvm_hilt_example.utils.preferences.SharedManager
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var store: SharedManager
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("hilt-example", store.token)
        Log.d("hilt-example", store.getBaseUrl())
        Log.d("hilt-example", store.userName)
        Log.d("hilt-example", store.token)
        navController = findNavController(R.id.nav_auth_host_fragment)

    }

    override fun onBackPressed() {
        if (!navController.popBackStack()) exitVariant()
    }


}