package ilyos.app.mvvm_hilt_example.repo.api.auth

import ilyos.app.mvvm_hilt_example.repo.api.oauth2.OauthToken
import ilyos.app.mvvm_hilt_example.repo.model.Register
import ilyos.app.mvvm_hilt_example.repo.model.ResetPass
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthServices {

    @POST("/oauth/token")
    suspend fun getAuth(@Body request: RequestBody): OauthToken

    @POST("/v1/users")
    suspend fun createUser(@Body register: Register): Register

    @POST("/v1/users/{email}/forgot-password")
    suspend fun forgotPassword(@Path("email") email : String) : ResponseBody

    @POST("/v1/users/reset-token/{token}")
    suspend fun resetPassword(@Path("token") token: String, @Body reset : ResetPass) : Register

    @POST("v1/users/{id}/send-confirmation-email")
    suspend fun resendConfirmation(@Path("id") id: Int) : Response<Void>

}