package com.kabe.quizapp.data.repository.trivia

import com.kabe.quizapp.data.base.BaseRepository
import com.kabe.quizapp.data.base.Resource
import com.kabe.quizapp.data.database.AppDatabase
import com.kabe.quizapp.data.network.TriviaService
import com.kabe.quizapp.domain.Trivia
import javax.inject.Inject

class TriviaRepository @Inject constructor(
    private val triviaService: TriviaService,
    private val appDatabase: AppDatabase
) : BaseRepository() {
    suspend fun getTrivia(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): Resource<List<Trivia>> = serviceCall {

        val result = triviaService.getTrivia(amount, category, difficulty, type)

        appDatabase.triviaDao().insertCurrentTrivia(result.results)

        result.results
    }

    suspend fun getResponseCode(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): Resource<Int> = serviceCall {

        val result = triviaService.getTrivia(amount, category, difficulty, type)

        result.responseCode
    }


    suspend fun getCachedTrivia(): Resource<List<Trivia>> = serviceCall {
        appDatabase.triviaDao().getAllCurrentTrivia()
    }

    suspend fun deleteAllTrivia() {
        appDatabase.triviaDao().deleteAllTrivia()
    }
}