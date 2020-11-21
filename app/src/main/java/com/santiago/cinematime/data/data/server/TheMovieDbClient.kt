package com.santiago.cinematime.data.data.server

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class TheMovieDbClient(baseUrl: String, apiKey: String) {

    companion object {
        const val DEFAULT_VERSION = 3
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(RequestInterceptor(apiKey))
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    inline fun <reified T> create(): T {
        return retrofit.get()
    }

    class RequestInterceptor(private val apiKey: String) : Interceptor {

        companion object {
            private const val PATH_PARAM_KEY = "api_key"
        }

        override fun intercept(chain: Interceptor.Chain): Response {

            val request = chain.request()

            val url = request.url().newBuilder()
                .addQueryParameter(PATH_PARAM_KEY, apiKey)
                .build()

            val newRequest = request.newBuilder()
                .url(url)
                .build()
            Timber.d(newRequest.url().toString())
            return chain.proceed(newRequest)
        }
    }

    inline fun <reified T> Retrofit.get(): T {
        return this.create(T::class.java)
    }

}


