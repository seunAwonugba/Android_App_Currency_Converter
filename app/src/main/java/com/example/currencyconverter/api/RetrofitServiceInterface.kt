package com.example.currencyconverter.api

import com.example.currencyconverter.constants.Constants.ACCESS_KEY
import com.example.currencyconverter.constants.Constants.LATEST_END_POINT
import com.example.currencyconverter.dataclass.CurrencyDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceInterface {

    @GET( LATEST_END_POINT)
    suspend fun getLatestExchangeRateInInterface(
        @Query("base") base : String,
        @Query("access_key")  access_key : String = ACCESS_KEY
    ) : Response<CurrencyDataClass>
}