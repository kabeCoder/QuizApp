package com.kabe.quizapp.ui.presentation.resultscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabe.quizapp.data.repository.trivia.TriviaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

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