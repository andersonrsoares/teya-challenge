package br.com.teya.challenge.network.di

import br.com.teya.challenge.network.retrofit.adapter.RemoteResultCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration

private const val  BASE_URL = "https://itunes.apple.com/us/rss/"

val NetworkModule = module {
    single { provideHTTPLoggingInterceptor() }

    single { provideOkHttpClient(get()) }

    single { provideMoshi() }

    single { provideRetrofit(get(), get()) }
}

private fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return interceptor;
}

private fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(Duration.ofSeconds(10))
        .connectTimeout(Duration.ofSeconds(10))
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun provideMoshi(): Moshi {
    return Moshi.Builder().build()
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RemoteResultCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
}