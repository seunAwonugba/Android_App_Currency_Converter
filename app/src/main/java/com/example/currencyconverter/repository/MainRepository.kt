package com.example.currencyconverter.repository

import com.example.currencyconverter.api.RetrofitServiceInterface
import com.example.currencyconverter.dataclass.CurrencyDataClass
import com.example.currencyconverter.utils.ApiCallErrorHandler
import javax.inject.Inject

// because we have already provided api interface in the module, HILT helps us the handle the dependency
// with API Interface, we can simply inject the whole API call that comprise of Retrofit now

//this implements the test repository
class MainRepository @Inject constructor(
    private val apiInterface : RetrofitServiceInterface
    ) : TestRepository {

    override suspend fun getDataFromApiInTestRepository(base: String): ApiCallErrorHandler<CurrencyDataClass> {
        return try {
            val receivedApiResponse = apiInterface.getLatestExchangeRateInInterface(base)
            val receivedApiResult = receivedApiResponse.body()

            if (receivedApiResponse.isSuccessful && receivedApiResult != null) {
                ApiCallErrorHandler.Success(receivedApiResult)
            } else {
                ApiCallErrorHandler.Error(receivedApiResponse.message())
            }
        } catch (e: Exception){
            ApiCallErrorHandler.Error(e.message ?: "An Error Occurred fetching data from the API ")
        }
    }
}