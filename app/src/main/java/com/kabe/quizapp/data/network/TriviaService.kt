package com.kabe.quizapp.data.network

import com.kabe.quizapp.data.model.response.TriviaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaService {

    @GET("api.php")
    suspend fun getTrivia(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String
    ): TriviaResponse

}