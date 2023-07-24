package com.example.gameofthrones.di

import android.content.Context
import com.example.gameofthrones.data.local.AppDatabase
import com.example.gameofthrones.data.local.HouseGameOfThronesDao
import com.example.gameofthrones.data.remote.HouseRemoteDataSource
import com.example.gameofthrones.data.remote.HouseService
import com.example.gameofthrones.data.repository.HouseRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://anapioficeandfire.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideHouseService(retrofit: Retrofit): HouseService = retrofit.create(HouseService::class.java)

    @Singleton
    @Provides
    fun provideHouseRemoteDataSource(houseService: HouseService) = HouseRemoteDataSource(houseService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideHouseDao(db: AppDatabase) = db.houseDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: HouseRemoteDataSource,
                          localDataSource: HouseGameOfThronesDao) =
        HouseRepository(remoteDataSource, localDataSource)
}