package ilyos.app.mvvm_hilt_example.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ilyos.app.mvvm_hilt_example.repo.api.oauth2.OauthToken
import javax.inject.Inject

class DataStore @Inject constructor(
    private val store: DataStore<Preferences>,
    private val gson: Gson
) {

    companion object {
        val TOKEN = preferencesKey<String>("token")
        val USER_NAME = preferencesKey<String>("username")
        val AUTH_TOKEN = preferencesKey<OauthToken>("auth_token")
        const val ATTEMPT = "ATTEMPT"
    }

    private val debug = "https://www.uz"
    private val prod = "https://www.uz"

    private fun <T> getData(json: String?): T {
        return gson.fromJson(json, object : TypeToken<T>() {}.type)
    }

    suspend fun saveToken (token: String) {
        store.edit { pr ->
            pr[TOKEN] = token
        }
    }


}