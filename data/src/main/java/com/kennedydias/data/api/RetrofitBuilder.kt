package com.kennedydias.data.api

import com.google.gson.GsonBuilder
import com.kennedydias.data.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitBuilder(
    private val cacheDir: File
) {

    private var cacheSize = 10 * 1024 * 1024 // 10 MB
    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val httpClient: OkHttpClient
        get() {
            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)

            val httpCacheDirectory = File(cacheDir, "http-cache")
            val cache = Cache(httpCacheDirectory, cacheSize.toLong())
            builder.cache(cache)

            builder.addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder().apply {
                    addHeader("Content-Type", "application/json; charset=utf-8")
                }

                chain.proceed(requestBuilder.build())
            }

            return builder.build()
        }

    fun <T> build(service: Class<T>): T {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.OMBD_API)
                .client(httpClient)
                .build()
        }

        return this.retrofit!!.create(service)
    }

}
