package me.prashant.nosamplenet.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.prashant.nosamplenet.data.repo.StockRepoImpl

class StockViewModelFactoryV2(
    private val context: Context,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockViewModelV2::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StockViewModelV2(StockRepoImpl(context)) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
