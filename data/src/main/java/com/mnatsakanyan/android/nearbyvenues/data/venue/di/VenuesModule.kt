package com.mnatsakanyan.android.nearbyvenues.data.venue.di

import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.VenueDataSource
import com.mnatsakanyan.android.nearbyvenues.data.venue.repository.VenueRecommendationsRepositoryImpl
import com.mnatsakanyan.android.nearbyvenues.domain.venue.VenueRecommendationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object VenuesModule {

    @Singleton
    @Provides
    fun providesVenueRecommendationsRepository(
            venueDataSource: VenueDataSource
    ): VenueRecommendationsRepository = VenueRecommendationsRepositoryImpl(
            venueDataSource,
            Dispatchers.IO
    )
}
