package me.prashant.nosamplenet.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.prashant.nosamplenet.data.model.Stock
import me.prashant.nosamplenet.databinding.ItemStocksBinding

class StockAdapterV2 : RecyclerView.Adapter<StockAdapterV2.StockViewHolder>() {
    private var list = listOf<Stock>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): StockViewHolder {
        val binding = ItemStocksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(
        holder: StockViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isNotEmpty()) {
            when (val payload = payloads[0]) {
                is StockUpdatePayLoadV2.NameUpdated -> {
                    holder.updateName(payload.newName)
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
        holder.bind(list[position])
    }

    fun submitList(newList: List<Stock>) {
        val callback = StockDiffUtilCallbackV2(list, newList)
        val result = DiffUtil.calculateDiff(callback)
        list = newList
        result.dispatchUpdatesTo(this)
    }

    inner class StockViewHolder(
        private val binding: ItemStocksBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stock: Stock) {
            binding.apply {
                stockName.text = stock.name
                stockSymbol.text = stock.symbol
                stockPrice.text = stock.price.toString()
                priceChange.text = stock.change.toString()
            }
        }

        fun updateName(name: String) {
            binding.stockName.text = name
        }
    }
}
