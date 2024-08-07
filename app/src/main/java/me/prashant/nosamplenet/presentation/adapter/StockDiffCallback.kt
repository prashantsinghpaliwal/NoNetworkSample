package me.prashant.nosamplenet.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import me.prashant.nosamplenet.data.model.Stock

class StockDiffCallback(
    private val oldList: List<Stock>,
    private val newList: List<Stock>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean = oldList[oldItemPosition].symbol == newList[newItemPosition].symbol

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean = oldList[oldItemPosition] == newList[newItemPosition]

    override fun getChangePayload(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Any? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        if (oldItem.name != newItem.name) {
            return StockUpdatePayload.NameChanged(newItem.name)
        }

        return null
    }
}
