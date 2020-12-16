package ilyos.app.mvvm_hilt_example.repo.model

data class Register(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val timezone: String? = null
)