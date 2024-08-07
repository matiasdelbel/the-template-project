package com.common.data.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun moshi(build: Moshi.Builder.() -> Unit = {}) = Moshi
    .Builder()
    .apply(build)
    .add(KotlinJsonAdapterFactory())
    .build()

fun okHttpClient(build: OkHttpClient.Builder.() -> Unit = {}): OkHttpClient = OkHttpClient
    .Builder()
    .apply(build)
    .addInterceptor(interceptor = HttpLoggingInterceptor().apply { level = BASIC })
    .build()

fun retrofit(build: Retrofit.Builder.() -> Unit = {}): Retrofit = Retrofit
    .Builder()
    .apply(build)
    .addConverterFactory(MoshiConverterFactory.create(moshi()))
    .build()
