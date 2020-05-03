package com.kpfu.itis.data.di

import com.kpfu.itis.data.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory,
        callAdapterFactory: RxJava2CallAdapterFactory,
        @Named(NAME_BASE_URL) baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named(NAME_API_KEY) apiKeyInterceptor: Interceptor,
        @Named(NAME_LOGGING) loggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    @Named(NAME_BASE_URL)
    fun provideBaseUrlString(): String = ""

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    @Named(NAME_LOGGING)
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    @Named(NAME_API_KEY)
    fun provideAuthInterceptor(): Interceptor = Interceptor { chain ->
        val newUrl = chain.request().url().newBuilder()
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .build()

        val newRequest = chain.request().newBuilder().url(newUrl).build()
        chain.proceed(newRequest)
    }
    companion object {
        private const val NAME_API_KEY = "ApiKeyInterceptor"
        private const val NAME_LOGGING = "LoggingInterceptor"
        private const val NAME_BASE_URL = "BaseURL"
    }

}