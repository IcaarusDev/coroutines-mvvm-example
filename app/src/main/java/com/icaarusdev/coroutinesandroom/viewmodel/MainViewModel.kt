package com.icaarusdev.coroutinesandroom.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icaarusdev.coroutinesandroom.model.CallApi
import com.icaarusdev.coroutinesandroom.model.Country
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {

    val countriesService = CallApi.getCountriesService()
    var job : Job? = null

    val countries = MutableLiveData<MutableList<Country>>()
    val countryLoadError = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler {coroutineContext, throwable ->
            onError("Exception: ${throwable.localizedMessage}")
    }

    fun refresh(){
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch{
            val response = countriesService.getCountries()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    countries.value = response.body()
                    countryLoadError.value = null
                    loading.value = false
                }else{
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String){
        countryLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}