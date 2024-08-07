package me.prashant.nosamplenet.presentation.adapter

sealed class StockUpdatePayload {
    data class NameChanged(val newName: String) : StockUpdatePayload()
}