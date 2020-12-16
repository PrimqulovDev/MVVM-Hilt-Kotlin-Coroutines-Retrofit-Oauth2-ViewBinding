package ilyos.app.mvvm_hilt_example.repo.model

import com.google.gson.annotations.SerializedName

data class APIError(
    @SerializedName("Message") var message: String,
    @SerializedName("Code") val code: String
)
