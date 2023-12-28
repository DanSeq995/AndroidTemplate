package it.dsequino.bitdrome.template.models

import com.google.gson.annotations.SerializedName

data class Root<T>(
    @SerializedName("status")
    val status: Int?,
    @SerializedName("error_code")
    val errorCode: Int?,
    @SerializedName("data")
    val data: T? = null
)