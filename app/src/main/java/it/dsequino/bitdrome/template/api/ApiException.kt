package it.dsequino.bitdrome.template.api

class ApiException(val errorCode: Int, val errorMessage: String) : Throwable()
