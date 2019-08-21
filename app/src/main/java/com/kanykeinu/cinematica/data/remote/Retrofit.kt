package com.kanykeinu.cinematica.data.remote

import android.content.Context
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.themoviedb.org/"
const val API_KEY  = "28e7c6ddf0fd55eaddd97cf21a0c2a81"

object Retrofit {

    fun getApi(context: Context) : MovieDbApi{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val authInterceptor = Interceptor{ chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            return@Interceptor chain.proceed(request)
        }

        val cacheControlInterceptor = Interceptor { chain ->
            val newBuilder = chain.request().newBuilder().header("Cache-Control", "public, max-age=" + 60*30)
                return@Interceptor chain.proceed(newBuilder.build())
        }


        val httpClient = OkHttpClient.Builder()
        httpClient
            .cache(
                Cache(context.cacheDir, 10 * 1024 * 1024))
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(cacheControlInterceptor)
            .retryOnConnectionFailure(true)

        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()


        return retrofit.create(MovieDbApi::class.java)
    }


}