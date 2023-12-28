package it.sermetra.cloud.laNuovaGuida.models.login

import com.google.gson.annotations.SerializedName

data class LoginInfo(
    @SerializedName("token")
    val token: String? = null,

    @SerializedName("username")
    val username: String? = null,

    @SerializedName("password")
    val password: String? = null
)
