package com.kabe.quizapp.data.network

import com.kabe.quizapp.constant.AppConstants
import com.kabe.quizapp.data.model.response.TriviaResponse
import com.kabe.quizapp.domain.Trivia
import retrofit2.http.GET

interface TriviaService {

    @GET(AppConstants.URL_ENDPOINT)
    suspend fun getTrivia(): TriviaResponse
}