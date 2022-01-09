package com.tyryshkin.newsapp.di

import android.content.Context
import com.tyryshkin.newsapp.data.network.ApiUtilities
import com.tyryshkin.newsapp.data.network.NewsApiService
import com.tyryshkin.newsapp.data.room.NewsDao
import com.tyryshkin.newsapp.data.room.NewsDatabase
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    private var retrofit: Retrofit? = null

    @Singleton
    @Provides
    fun provideNewsDatabase(context: Context): NewsDatabase {
        return NewsDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.getNewsDao()
    }

    @Provides
    fun getNewsApiService(): NewsApiService {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(ApiUtilities.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }
        return retrofit!!.create(NewsApiService::class.java)
    }
}