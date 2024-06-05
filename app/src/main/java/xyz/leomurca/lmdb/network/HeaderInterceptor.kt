package xyz.leomurca.lmdb.network

import okhttp3.Interceptor
import okhttp3.Response
import xyz.leomurca.lmdb.BuildConfig

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithHeaders = chain.request().newBuilder().apply {
            header(AUTHORIZATION, "Bearer ${BuildConfig.API_KEY}")
        }.build()

        return chain.proceed(requestWithHeaders)
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}