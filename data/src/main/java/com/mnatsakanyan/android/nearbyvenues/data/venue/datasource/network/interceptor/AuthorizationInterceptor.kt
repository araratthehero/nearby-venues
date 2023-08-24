package com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

internal class AuthorizationInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
                .request()
                .newBuilder()
                .addHeader(HEADER_AUTHORIZATION, apiKey)
                .build()

        return chain.proceed(request)
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
    }
}
