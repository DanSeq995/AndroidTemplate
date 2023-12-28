package it.dsequino.bitdrome.template.api

import it.sermetra.cloud.laNuovaGuida.models.login.LoginBody
import it.sermetra.cloud.laNuovaGuida.models.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface BDApi {
    @POST("1.0/auth/login")
    fun login(
        @Body loginBody: LoginBody
    ): Call<LoginResponse>
}