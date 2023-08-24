package com.mnatsakanyan.android.nearbyvenues.data.location.di

import android.app.Application
import android.content.Context
import android.location.LocationManager
import com.mnatsakanyan.android.nearbyvenues.data.location.datasource.LocationDataSource
import com.mnatsakanyan.android.nearbyvenues.data.location.repository.LocationRepositoryImpl
import com.mnatsakanyan.android.nearbyvenues.domain.location.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocationModule {

    @Singleton
    @Provides
    fun providesFusedLocationProviderClient(
            application: Application
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application)

    @Singleton
    @Provides
    fun providesLocationManager(
            application: Application
    ): LocationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @Singleton
    @Provides
    fun providesLocationRepository(
            locationDataSource: LocationDataSource
    ): LocationRepository = LocationRepositoryImpl(
            locationDataSource,
            Dispatchers.IO
    )
}
