package ilyos.app.mvvm_hilt_example.repo

import ilyos.app.mvvm_hilt_example.repo.api.auth.AuthServices
import ilyos.app.mvvm_hilt_example.repo.api.oauth2.OauthToken
import ilyos.app.mvvm_hilt_example.repo.model.Register
import ilyos.app.mvvm_hilt_example.repo.model.ResetPass
import ilyos.app.mvvm_hilt_example.repo.model.Resource
import ilyos.app.mvvm_hilt_example.repo.model.ResponseHandler
import ilyos.app.mvvm_hilt_example.utils.Constants
import okhttp3.FormBody
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authServices: AuthServices
) {

    private val responseHandler = ResponseHandler()

    suspend fun getAuth(): Resource<OauthToken> {

        return try {
            responseHandler.handleSuccess(
                authServices.getAuth(
                    FormBody.Builder()
                        .add(Constants.GRANT_TYPE, "client_credentials")
                        .build()
                )
            )
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun getAuth(username: String, password: String): Resource<OauthToken> {
        return try {
            responseHandler.handleSuccess(
                authServices.getAuth(
                    FormBody.Builder()
                        .add(Constants.GRANT_TYPE, Constants.PASSWORD)
                        .add(Constants.USER_NAME, username)
                        .add(Constants.PASSWORD, password)
                        .build()
                )
            )
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun createUser(register: Register): Resource<Register> {
        return try {
            responseHandler.handleSuccess(authServices.createUser(register))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun forgotPassword(email: String): Resource<ResponseBody> = try {
        responseHandler.handleSuccess(authServices.forgotPassword(email))
    } catch (e: Exception) {
        responseHandler.handleException(e)
    }

    suspend fun resetPassword(token: String, password: String): Resource<Register> {
        return try {
            responseHandler.handleSuccess(authServices.resetPassword(token, ResetPass(password, password)))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun resendConfirmation(id: Int): Resource<Response<Void>> {
        return try {
            responseHandler.handleSuccess(authServices.resendConfirmation(id))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}