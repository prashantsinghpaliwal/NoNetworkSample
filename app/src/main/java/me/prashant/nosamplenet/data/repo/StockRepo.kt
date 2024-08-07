package me.prashant.nosamplenet.data.repo

import me.prashant.nosamplenet.data.model.Stock

interface StockRepo {
    fun getStocksList(): List<Stock>
}
