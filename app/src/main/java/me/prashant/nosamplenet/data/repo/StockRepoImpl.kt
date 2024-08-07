package me.prashant.nosamplenet.data.repo

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.prashant.nosamplenet.data.model.Stock

class StockRepoImpl(
    private val context: Context,
) : StockRepo {
    override fun getStocksList(): List<Stock> {
        val textJson =
            context.assets
                .open("stocks.json")
                .bufferedReader()
                .use {
                    it.readText()
                }

        val stockType = object : TypeToken<List<Stock>>() {}.type
        val newList = Gson().fromJson<List<Stock>>(textJson, stockType)
        return newList
    }
}
