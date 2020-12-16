package ilyos.app.mvvm_hilt_example.repo.model

import ilyos.app.mvvm_hilt_example.utils.ErrorUtils
import retrofit2.HttpException
import java.net.SocketTimeoutException

open class ResponseHandler {

    fun <T : Any> handleSuccess(data: T): Resource<T> = Resource.success(data)

    fun <T : Any> handleException(e: Exception): Resource<T> = when (e) {
        is HttpException ->
            Resource.error(message = ErrorUtils.parseError(e.response()).message, data = null)
        is SocketTimeoutException ->
            Resource.error(message = getErrorMessage(ErrorCodes.SocketTimeOut.code), data = null)
        else -> Resource.error(message = getErrorMessage(Int.MAX_VALUE), data = null)
    }

    private fun getErrorMessage(code: Int): String = when (code) {
        ErrorCodes.SocketTimeOut.code -> "Timeout"
        401 -> "Unauthorized"
        404 -> "Not found"
        else -> code.toString()
    }
}

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}
