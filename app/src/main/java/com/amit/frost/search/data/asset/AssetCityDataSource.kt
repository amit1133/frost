package com.amit.frost.search.data.asset

import android.content.Context
import com.amit.frost.search.domain.City
import com.amit.frost.search.domain.CityDataSource
import kotlinx.serialization.json.Json
import java.io.InputStream

class AssetCityDataSource(private val context: Context) : CityDataSource {
    override suspend fun getCities(): List<City> {
        val json = try {
            val inputStream: InputStream = context.assets.open("ZilaList.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return emptyList() // Return empty list in case of error
        }
        return Json.decodeFromString(json)
    }
}