package com.icaarusdev.coroutinesandroom.model

import androidx.lifecycle.MutableLiveData
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {

    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): Response<MutableList<Country>>
}