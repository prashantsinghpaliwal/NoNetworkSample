package me.prashant.nosamplenet.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.prashant.nosamplenet.data.repo.StockRepository

class StockViewModelFactory(
    private val context: Context,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StockViewModel(StockRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
