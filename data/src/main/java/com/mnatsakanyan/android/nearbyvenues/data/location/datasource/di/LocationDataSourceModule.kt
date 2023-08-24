package com.mnatsakanyan.android.nearbyvenues.data.location.datasource.di

import com.mnatsakanyan.android.nearbyvenues.data.location.datasource.LocationDataSource
import com.mnatsakanyan.android.nearbyvenues.data.location.datasource.LocationDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface LocationDataSourceModule {

    @Binds
    fun bindLocationDataSource(
            locationDataSource: LocationDataSourceImpl
    ): LocationDataSource
}
