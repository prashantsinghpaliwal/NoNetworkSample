package me.prashant.nosamplenet.presentation.adapter

sealed class StockUpdatePayLoadV2 {
    data class NameUpdated(
        val newName: String,
    ) : StockUpdatePayLoadV2()
}
