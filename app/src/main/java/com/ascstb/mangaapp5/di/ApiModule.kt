package com.ascstb.mangaapp5.di

import com.ascstb.mangaapp5.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { createOkHttpClient() }
}

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })
    .readTimeout(120, TimeUnit.SECONDS)
    .build()

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T =
    Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .addConverterFactory(JspoonConverterFactory.create())
        .build()
        .create(T::class.java)


private val logLevel =
    if (!BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE
    else HttpLoggingInterceptor.Level.BODY