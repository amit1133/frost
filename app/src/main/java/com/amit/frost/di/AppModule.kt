package com.amit.frost.di

import com.amit.frost.core.data.local.LocalDataStoreLatLonDataSource
import com.amit.frost.core.data.networking.HttpClientFactory
import com.amit.frost.core.domain.LatLonDataSource
import com.amit.frost.home.data.local.LocalLocationDataSource
import com.amit.frost.home.data.networking.RemoteWeatherInfoDataSource
import com.amit.frost.home.domain.LocationDataSource
import com.amit.frost.home.domain.WeatherInfoDataSource
import com.amit.frost.home.presentation.WeatherInfoViewModel
import com.amit.frost.search.data.asset.AssetCityDataSource
import com.amit.frost.search.domain.CityDataSource
import com.amit.frost.search.presentation.SearchViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    viewModelOf(::WeatherInfoViewModel)
    singleOf(::RemoteWeatherInfoDataSource).bind<WeatherInfoDataSource>()
    viewModelOf(::SearchViewModel)
    singleOf(::AssetCityDataSource).bind<CityDataSource>()
    singleOf(::LocalDataStoreLatLonDataSource).bind<LatLonDataSource>()
    singleOf(::LocalLocationDataSource).bind<LocationDataSource>()

}
