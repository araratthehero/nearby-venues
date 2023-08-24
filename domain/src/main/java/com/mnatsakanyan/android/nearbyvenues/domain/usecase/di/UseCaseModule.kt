package com.mnatsakanyan.android.nearbyvenues.domain.usecase.di

import com.mnatsakanyan.android.nearbyvenues.domain.usecase.GetVenueRecommendationsUseCase
import com.mnatsakanyan.android.nearbyvenues.domain.usecase.GetVenueRecommendationsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    internal abstract fun bindVenueRecommendationsUseCase(
            getVenueRecommendationsUseCase: GetVenueRecommendationsUseCaseImpl
    ): GetVenueRecommendationsUseCase
}

