package com.kabe.quizapp.data.repository.trivia

import com.kabe.quizapp.data.base.BaseRepository
import com.kabe.quizapp.data.base.Resource
import com.kabe.quizapp.data.model.response.TriviaResponse
import com.kabe.quizapp.data.network.TriviaService
import com.kabe.quizapp.domain.Trivia
import javax.inject.Inject

class TriviaRepository @Inject constructor(
    private val triviaService: TriviaService
) : BaseRepository() {
    suspend fun getTrivia (): Resource<List<Trivia>> = serviceCall {

        val result = triviaService.getTrivia()

        result.results
    }
}