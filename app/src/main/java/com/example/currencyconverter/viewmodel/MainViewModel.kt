package com.example.currencyconverter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.currencyconverter.repository.TestRepository
import com.example.currencyconverter.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//Please note i extend the test view model reason being that its an interface and i can easily
//implement its members

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository : TestRepository,
    private var dispatchers : DispatcherProvider
) : ViewModel() {

    sealed class CurrencyEvent{
        class Success(val resultText : String) : CurrencyEvent()
        class Failure(val errorText : String) : CurrencyEvent()
        //object because it not taking any parameter
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

    //using state flow
    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion : StateFlow<CurrencyEvent> = _conversion

    //create a function that converts the currency and makes the request

}