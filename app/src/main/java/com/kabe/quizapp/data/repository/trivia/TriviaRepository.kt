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
    suspend fun getTrivia (): Resource<List<Trivia>> = serviceCall {

        val result = triviaService.getTrivia()

        //appDatabase.triviaDao().insertCurrentTrivia(result.results)

        result.results
    }

    suspend fun getCachedTrivia(): Resource<List<Trivia>> = serviceCall {
        appDatabase.triviaDao().getAllCurrentTrivia()
    }
}