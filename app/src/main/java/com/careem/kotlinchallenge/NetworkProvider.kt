package com.careem.kotlinchallenge

import com.careem.kotlinchallenge.models.QuizResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val QUIZ_BASE_URL = "https://gist.githubusercontent.com"

class NetworkProvider {

    private fun provideHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            @Suppress("ConstantConditionIf")
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder().addInterceptor(logger).addInterceptor(MockInterceptor()).build()
    }

    fun provideRetrofitQuizService(): QuizService {
        return Retrofit.Builder()
            .client(provideHttpClient())
            .baseUrl(QUIZ_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizService::class.java)
    }
}

interface QuizService {
    @GET("/AamirAbro/7abe426f0f01f58140e826b19f020a8b/raw/58eb42d6a2925e066805eb96612ee33718316b7d/KoltinChallenge.json")
    fun getQuizList() : Single<QuizResponse>
}

