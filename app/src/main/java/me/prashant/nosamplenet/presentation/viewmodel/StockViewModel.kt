package me.prashant.nosamplenet.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.prashant.nosamplenet.data.model.Stock
import me.prashant.nosamplenet.data.repo.StockRepository

class StockViewModel(private val repository: StockRepository) : ViewModel() {

    private val _stocks = MutableLiveData<List<Stock>>()
    val stocks: LiveData<List<Stock>> get() = _stocks

    private val refreshInterval = 1L

    fun fetchStocks() {
        viewModelScope.launch {
            while (true) {
                fetchAndUpdateStocks()
                delay(refreshInterval * 1000)
            }
        }
    }

    private fun fetchAndUpdateStocks() {
        val stockList = repository.getStocks()
        _stocks.value = stockList
    }
}