package me.prashant.nosamplenet.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import me.prashant.nosamplenet.R
import me.prashant.nosamplenet.databinding.ActivityMainBinding
import me.prashant.nosamplenet.presentation.adapter.StockAdapter
import me.prashant.nosamplenet.presentation.viewmodel.StockViewModel
import me.prashant.nosamplenet.presentation.viewmodel.StockViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: StockViewModel by viewModels { StockViewModelFactory(this) }
    private val adapter = StockAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDefaultPaddings()
        setUpUi()
        setUpObservers()
        getData()
    }

    private fun getData() {
        viewModel.fetchStocks()
    }

    private fun setUpObservers() {
        viewModel.stocks.observe(this) {
            it?.let {
                adapter.submitList(it)
            }
        }
    }

    private fun setUpUi() {
        binding.stockList.adapter = adapter
    }

    private fun setDefaultPaddings() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
