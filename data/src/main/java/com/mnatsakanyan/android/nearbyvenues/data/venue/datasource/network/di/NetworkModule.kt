package com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.network.di

import com.mnatsakanyan.android.nearbyvenues.data.BuildConfig
import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.network.PlacesService
import com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.network.interceptor.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Singleton
    @Provides
    fun providesAuthorizationInterceptor() = AuthorizationInterceptor(BuildConfig.API_KEY)

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
                            authorizationInterceptor: AuthorizationInterceptor): OkHttpClient =
            OkHttpClient
                    .Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(authorizationInterceptor)
                    .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun providePlacesService(retrofit: Retrofit): PlacesService =
            retrofit.create(PlacesService::class.java)
}
