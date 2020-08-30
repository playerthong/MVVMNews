package com.playerthong.androidnews.api

import com.google.gson.Gson
import com.playerthong.androidnews.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiRetrofit {
    companion object{
        private val ENDPOINT: String = BuildConfig.API_SERVICE_URI_BASE.toString() + "/v2/"
        private var retrofit: Retrofit? = null
        private var client = OkHttpClient()
        private var service: ApiService? = null
        fun getRetrofit(): Retrofit? {
            if (retrofit == null) {
                val builder: OkHttpClient.Builder
                builder = OkHttpClient.Builder()
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
                builder.addInterceptor(interceptor)
                builder.readTimeout(60, TimeUnit.SECONDS)
                builder.connectTimeout(60, TimeUnit.SECONDS)
                client = builder.build()
                retrofit = Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .client(client)
                    .build()
            }
            return retrofit
        }

        fun getService(): ApiService? {
            if (service == null) {
                retrofit = getRetrofit()
                service = retrofit!!.create<ApiService>(ApiService::class.java)
            } else {
            }
            return service
        }
    }

}