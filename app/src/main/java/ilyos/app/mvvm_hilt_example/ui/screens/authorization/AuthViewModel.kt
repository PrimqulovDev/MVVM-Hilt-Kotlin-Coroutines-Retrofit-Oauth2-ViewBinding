package ilyos.app.mvvm_hilt_example.ui.screens.authorization

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.liveData
import ilyos.app.mvvm_hilt_example.base.BaseViewModel
import ilyos.app.mvvm_hilt_example.repo.AuthRepository
import ilyos.app.mvvm_hilt_example.repo.model.Register
import ilyos.app.mvvm_hilt_example.repo.model.Resource
import kotlinx.coroutines.Dispatchers

class AuthViewModel @ViewModelInject constructor(private val repository: AuthRepository) :
    BaseViewModel() {

    fun createUser(register: Register) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(repository.createUser(register))
    }

    fun getAuth(username: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(repository.getAuth(username, password))
    }


    fun forgotPassword(email: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(repository.forgotPassword(email))
    }

}