package com.amit.frost.core.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.amit.frost.core.domain.LatLon
import com.amit.frost.core.domain.LatLonDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataStoreLatLonDataSource(private val context: Context) : LatLonDataSource {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "lat_lon")
    private val dataStore = context.dataStore
    override suspend fun saveLatLon(latLon: LatLon) {
        dataStore.edit { preferences ->
            preferences[doublePreferencesKey("lat")] = latLon.lat
            preferences[doublePreferencesKey("lon")] = latLon.lon
        }
    }

    override fun getLatLon(): Flow<LatLon> {
        return dataStore.data.map { preferences ->
            LatLon(
                lat = preferences[doublePreferencesKey("lat")] ?: 0.0,
                lon = preferences[doublePreferencesKey("lon")] ?: 0.0
            )
        }
    }

}