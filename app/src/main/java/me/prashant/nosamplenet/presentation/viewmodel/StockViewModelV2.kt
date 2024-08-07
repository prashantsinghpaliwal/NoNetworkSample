package me.prashant.nosamplenet.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.prashant.nosamplenet.data.model.Stock
import me.prashant.nosamplenet.data.repo.StockRepo

class StockViewModelV2(private val stockRepo: StockRepo) : ViewModel(){

    private val _stockList = MutableLiveData<List<Stock>>()
    val stockList : LiveData<List<Stock>> = _stockList

    private val refreshInterval = 1L

    fun getStockList() {
        viewModelScope.launch {
            while (true) {
                fetchAndUpdateStocks()
                delay(refreshInterval * 1000)
            }
        }
    }

    private fun fetchAndUpdateStocks() {
        val stockList = stockRepo.getStocksList()
        _stockList.value = stockList
    }

}