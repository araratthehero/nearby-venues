package com.mnatsakanyan.android.nearbyvenues

import com.mnatsakanyan.android.nearbyvenues.domain.usecase.di.UseCaseModule
import com.mnatsakanyan.android.nearbyvenues.domain.usecase.GetVenueRecommendationsUseCase
import com.mnatsakanyan.android.nearbyvenues.domain.venue.fake.FakeGetVenueRecommendationsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
        components = [SingletonComponent::class],
        replaces = [UseCaseModule::class],
)
object TestUseCaseModule {

    @Singleton
    @Provides
    fun providesVenueRecommendationsUseCase(): GetVenueRecommendationsUseCase =
            FakeGetVenueRecommendationsUseCase()
}
