package com.kabe.quizapp.data.model.response

import com.google.gson.annotations.SerializedName
import com.kabe.quizapp.domain.Trivia
import java.io.Serializable

data class TriviaResponse(
    @SerializedName("response_code") var responseCode: Int,
    @SerializedName("results") var results: List<Trivia>
) : Serializable