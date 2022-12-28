package com.kabe.quizapp.data.di

import android.content.Context
import androidx.room.Room
import com.kabe.quizapp.BuildConfig.DEBUG
import com.kabe.quizapp.constant.AppConstants
import com.kabe.quizapp.data.base.RetrofitBuilder
import com.kabe.quizapp.data.database.AppDatabase
import com.kabe.quizapp.data.network.TriviaService
import com.kabe.quizapp.data.repository.trivia.TriviaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TIMEOUT = 20000 //20 seconds
    private const val DATABASE_NAME = "quiz_app_database"

    // Retrofit Builder
    @Provides
    @Singleton
    fun provideClient(@ApplicationContext appContext: Context): OkHttpClient {
        return OkHttpClient.Builder().apply {

            connectTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            readTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)

            addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            })

            addInterceptor(RetrofitBuilder.NoInternetInterceptor(appContext))

        }.build()
    }

    @Provides
    @Singleton
    fun createRetrofitInstance(client: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(AppConstants.BASE_URL)

        client(client)
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
        appDatabase: AppDatabase
    ): TriviaRepository {
        return TriviaRepository(triviaService, appDatabase)
    }

    @Provides
    @Singleton
    fun provideTriviaDb(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
}