package me.prashant.nosamplenet.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import me.prashant.nosamplenet.databinding.ActivityMainBinding
import me.prashant.nosamplenet.presentation.adapter.StockAdapterV2
import me.prashant.nosamplenet.presentation.viewmodel.StockViewModelFactoryV2
import me.prashant.nosamplenet.presentation.viewmodel.StockViewModelV2

class MainActivityV2 : AppCompatActivity() {
    private lateinit var mainActivityMainBinding: ActivityMainBinding
    private val viewModel: StockViewModelV2 by viewModels { StockViewModelFactoryV2(this) }
    private val adapter = StockAdapterV2()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityMainBinding.root)
        setUpObservers()
        setUpUi()
    }

    private fun setUpUi() {
        mainActivityMainBinding.stockList.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.getStockList()
        viewModel.stockList.observe(this) {
            adapter.submitList(it)
        }
    }
}
