package com.kabe.quizapp.data.di

import com.kabe.quizapp.constant.AppConstants
import com.kabe.quizapp.data.network.TriviaService
import com.kabe.quizapp.data.repository.trivia.TriviaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun createRetrofitInstance(): Retrofit = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(AppConstants.BASE_URL)

    }.build()

    @Provides
    @Singleton
    fun provideTriviaService(retrofit: Retrofit): TriviaService {
        return retrofit.create(TriviaService::class.java)
    }

    @Provides
    @Singleton
    fun provideTriviaRepository(
        triviaService: TriviaService,
    ): TriviaRepository {
        return TriviaRepository(triviaService)
    }
}