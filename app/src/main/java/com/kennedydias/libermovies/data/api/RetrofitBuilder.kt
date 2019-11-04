package com.kennedydias.libermovies.data.api

import com.google.gson.GsonBuilder
import com.kennedydias.libermovies.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {

    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val httpClient: OkHttpClient
        get() {
            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
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
