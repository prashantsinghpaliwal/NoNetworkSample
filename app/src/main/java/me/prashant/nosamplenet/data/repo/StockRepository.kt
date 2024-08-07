package me.prashant.nosamplenet.data.repo

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.prashant.nosamplenet.data.model.Stock

class StockRepository(
    private val context: Context,
) {
    private var counter = 0

    fun getStocks(): List<Stock> {
        val json =
            context.assets
                .open("stocks.json")
                .bufferedReader()
                .use { it.readText() }
        val stockType = object : TypeToken<List<Stock>>() {}.type

        val serialJson = Gson().fromJson<List<Stock>>(json, stockType)

        val list = serialJson?.toMutableList()
        val newList =
            list?.map {
                it.copy(name = it.name.plus(counter))
            } ?: emptyList()

        counter++
        return newList
    }
}
