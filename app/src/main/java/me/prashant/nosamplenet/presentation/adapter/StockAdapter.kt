package me.prashant.nosamplenet.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.prashant.nosamplenet.data.model.Stock
import me.prashant.nosamplenet.databinding.ItemStocksBinding

class StockAdapter : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {
    private var stocks: List<Stock> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): StockViewHolder {
        val binding = ItemStocksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: StockViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isNotEmpty()) {
            when (payloads[0]) {
                is StockUpdatePayload.NameChanged -> {
                    holder.bindName((payloads[0] as StockUpdatePayload.NameChanged).newName)
                }
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(
        holder: StockViewHolder,
        position: Int,
    ) {
        holder.bind(stocks[position])
    }

    override fun getItemCount(): Int = stocks.size

    fun submitList(newStocks: List<Stock>) {
        val diffCallback = StockDiffCallback(stocks, newStocks)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        stocks = newStocks
        diffResult.dispatchUpdatesTo(this)
    }

    class StockViewHolder(
        private val binding: ItemStocksBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stock: Stock) {
            binding.stockName.text = stock.name
            binding.stockSymbol.text = stock.symbol
            binding.stockPrice.text = stock.price.toString()
            binding.priceChange.text = stock.change.toString()
        }

        fun bindName(newName: String) {
            binding.stockName.text = newName
        }
    }
}
