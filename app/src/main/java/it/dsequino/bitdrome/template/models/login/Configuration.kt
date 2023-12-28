package it.sermetra.cloud.laNuovaGuida.models.login

import com.google.gson.annotations.SerializedName

data class Configuration(
    @SerializedName("officeType")
    val officeType: String? = null,

    @SerializedName("authorizations")
    val authorizations: ArrayList<String>? = null
)
