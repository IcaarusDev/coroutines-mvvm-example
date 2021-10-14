package com.icaarusdev.coroutinesandroom.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CallApi {
        private var BASE_URL = "https://raw.githubusercontent.com"

    fun getCountriesService(): CountriesApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }
}