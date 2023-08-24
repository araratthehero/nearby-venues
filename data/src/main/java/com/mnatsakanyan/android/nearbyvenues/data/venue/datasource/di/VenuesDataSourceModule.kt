package com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.di

import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.VenueDataSource
import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.VenueDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Binds
    fun bindVenueDataSource(
            venueDataSource: VenueDataSourceImpl
    ): VenueDataSource
}
