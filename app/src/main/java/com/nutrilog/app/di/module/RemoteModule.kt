package com.nutrilog.app.di.module

import com.google.gson.GsonBuilder
import com.nutrilog.app.BuildConfig
import com.nutrilog.app.data.pref.UserSessionPreference
import com.nutrilog.app.data.remote.DateAdapter
import com.nutrilog.app.data.remote.GenderAdapter
import com.nutrilog.app.data.remote.HeaderInterceptor
import com.nutrilog.app.data.remote.IntToFloatAdapter
import com.nutrilog.app.data.remote.ResponseStatusAdapter
import com.nutrilog.app.data.remote.service.AuthService
import com.nutrilog.app.data.remote.service.NutritionService
import com.nutrilog.app.domain.common.StatusResponse
import com.nutrilog.app.domain.model.Gender
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit

val remoteModule =
    module {
        single { provideHttpClient(get()) }
        single { provideRetrofit(get()) }
        single { provideAuthService(get()) }
        single { provideNutritionService(get()) }
    }

fun provideHttpClient(userSessionPreference: UserSessionPreference): OkHttpClient {
    return OkHttpClient
        .Builder()
        .addInterceptor(HeaderInterceptor(userSessionPreference))
        .addInterceptor(loggingInterceptor)
        .readTimeout(120, TimeUnit.SECONDS)
        .connectTimeout(120, TimeUnit.SECONDS)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val gson =
        GsonBuilder()
            .registerTypeAdapter(Gender::class.java, GenderAdapter())
            .registerTypeAdapter(Date::class.java, DateAdapter())
            .registerTypeAdapter(Float::class.java, IntToFloatAdapter())
            .registerTypeAdapter(StatusResponse::class.java, ResponseStatusAdapter())
            .create()

    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

private val loggingInterceptor =
    if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

fun provideNutritionService(retrofit: Retrofit): NutritionService = retrofit.create(NutritionService::class.java)
