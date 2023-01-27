package com.kabe.quizapp.ui.presentation.resultscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabe.quizapp.data.base.Status
import com.kabe.quizapp.data.repository.trivia.TriviaRepository
import com.kabe.quizapp.domain.Trivia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor(
    private val triviaRepository: TriviaRepository
) : ViewModel() {

    init {
        deleteCachedTrivia()
    }

    private fun deleteCachedTrivia() {
        viewModelScope.launch {
            triviaRepository.deleteAllTrivia()
        }
    }
}