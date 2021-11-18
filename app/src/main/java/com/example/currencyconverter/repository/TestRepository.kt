package com.example.currencyconverter.repository

import com.example.currencyconverter.dataclass.CurrencyDataClass
import com.example.currencyconverter.utils.ApiCallErrorHandler

interface TestRepository {

    //Note i didn't pass the API key in the constructor, reason being that we have already initialised
    //it in the API Interface, however the base will be passed dynamically to query the api call
    suspend fun getDataFromApiInTestRepository(base : String) : ApiCallErrorHandler<CurrencyDataClass>
}