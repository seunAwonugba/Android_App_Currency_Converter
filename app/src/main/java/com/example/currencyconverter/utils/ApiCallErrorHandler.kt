package com.example.currencyconverter.utils


//If data was received from API call, attach data no error message else otherwise
sealed class ApiCallErrorHandler<T>(val data : T?, val message : String?) {
    //when success, do this
    class Success<T>(data: T) : ApiCallErrorHandler<T>(data,null)
    //when error, get the error message
    class Error<T>(message: String) : ApiCallErrorHandler<T>(null, message)
}