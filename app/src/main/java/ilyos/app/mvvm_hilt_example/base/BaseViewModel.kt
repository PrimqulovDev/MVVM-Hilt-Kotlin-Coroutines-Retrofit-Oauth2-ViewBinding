package ilyos.app.mvvm_hilt_example.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ilyos.app.mvvm_hilt_example.repo.AuthRepository
import ilyos.app.mvvm_hilt_example.repo.api.oauth2.OauthToken
import ilyos.app.mvvm_hilt_example.repo.model.Resource
import ilyos.app.mvvm_hilt_example.utils.preferences.SharedManager
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Developed by Ilyos
 */

open class BaseViewModel : ViewModel() {

    @LayoutRes
    var parentLayoutId: Int = 0

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var sharedManager: SharedManager

    @Inject
    lateinit var authRepository: AuthRepository


    val data = MutableLiveData<Any>()
    val singleData = MutableLiveData<Any>()
    val oauthToken = MutableLiveData<OauthToken>()

    fun getAuth() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(authRepository.getAuth())
    }

}