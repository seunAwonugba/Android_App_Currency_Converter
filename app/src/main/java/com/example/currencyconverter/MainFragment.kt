package com.example.currencyconverter

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.currencyconverter.databinding.FragmentMainBinding
import com.example.currencyconverter.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        val fromCurrencyCodes = resources.getStringArray(R.array.currency_codes)
        val fromAdapter = ArrayAdapter(
            requireContext(), R.layout.dropdown_list_layout, fromCurrencyCodes
        )
        binding.fromCurrencyCodeTextViewId.setAdapter(fromAdapter)

        val toCurrencyCodes = resources.getStringArray(R.array.currency_codes)
        val toAdapter = ArrayAdapter(
            requireContext(), R.layout.dropdown_list_layout, toCurrencyCodes
        )
        binding.toCurrencyCodeTextViewId.setAdapter(toAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)




        binding.button.setOnClickListener {
            var fromCurrency = binding.fromCurrencyCodeTextViewId.text.toString()
            var toCurrency = binding.toCurrencyCodeTextViewId.text.toString()
            var inputAmount = binding.amountToConvertTextViewId.text.toString()

            viewModel.convert(
                fromCurrency = fromCurrency,
                toCurrency = toCurrency,
                userInputAmountToConvert = "100"
            )
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.conversion.collect { event->
                when(event){
                    is MainViewModel.CurrencyEvent.Success -> {
                        loadingStateVisibilityHandler()
                        binding.resultTextViewId.text = event.resultText
                        binding.resultTextViewId.setTextColor(Color.BLACK)
                        loadingStateInvisibilityHandler()
                    }
                    is MainViewModel.CurrencyEvent.Failure -> {
                        loadingStateInvisibilityHandler()
                        binding.resultTextViewId.text = event.errorText
                        binding.resultTextViewId.setTextColor(Color.RED)
                    }
                    is MainViewModel.CurrencyEvent.Loading -> {
                        loadingStateVisibilityHandler()
                    }
                    else -> Unit
                }
            }
        }

    }

    private fun loadingStateVisibilityHandler(){
        binding.loadingView.visibility = View.VISIBLE
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun loadingStateInvisibilityHandler(){
        binding.loadingView.visibility = View.GONE
        binding.progressbar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}