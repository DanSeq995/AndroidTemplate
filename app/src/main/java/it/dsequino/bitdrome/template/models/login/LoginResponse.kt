package it.sermetra.cloud.laNuovaGuida.models.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String? = null,

    @SerializedName("configuration")
    val configuration: Configuration? = null
)
