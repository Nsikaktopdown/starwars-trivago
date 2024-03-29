package io.altalabs.androidbase.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.altalabs.androidbase.BuildConfig
import io.altalabs.androidbase.util.NetworkMonitor
import io.altalabs.api.boilerplate.AppRemoteImpl
import io.altalabs.api.boilerplate.RemoteApi
import io.altalabs.data.boilerplate.exception.NoNetworkException
import io.altalabs.data.boilerplate.repository.AppRemote
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
 class NetworkModule {

    private val CONNECT_TIMEOUT = 10L
    private val READ_TIMEOUT = 10L
    private val WRITE_TIMEOUT = 10L

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun provideRemoteApi(retrofit: Retrofit): RemoteApi = retrofit.create(RemoteApi::class.java)

    @Provides
    @Singleton
   fun provideAppRemote(appRemoteImpl: AppRemoteImpl): AppRemote = appRemoteImpl

    @Provides @Singleton
    fun providesInternetConnectionInterceptor(networkMonitor: NetworkMonitor): Interceptor {
        return Interceptor { chain ->
            if (networkMonitor.isConnected()) {
                chain.proceed(chain.request())
            } else {
                throw NoNetworkException()
            }
        }
    }
}