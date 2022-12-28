package com.kabe.quizapp.ui.presentation.quizscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabe.quizapp.data.base.Status
import com.kabe.quizapp.data.repository.trivia.TriviaRepository
import com.kabe.quizapp.domain.Trivia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class QuizScreenViewModel @Inject constructor(
    private val triviaRepository: TriviaRepository
) : ViewModel() {

    private val _errorMessage = MutableSharedFlow<String?>()
    val errorMessage: SharedFlow<String?> = _errorMessage.asSharedFlow()

   private val triviaList = mutableListOf<Trivia>()
   private val _trivia = MutableSharedFlow<MutableList<Trivia>>()
   val trivia: SharedFlow<List<Trivia>> = _trivia.asSharedFlow()

    init {
        getTrivia()
    }

    private fun getTrivia() {
        viewModelScope.launch {
            val triviaResult = triviaRepository.getTrivia()
            when(triviaResult.status) {
                Status.SUCCESS -> triviaResult.data?.let { trivia ->
                    _trivia.emit(trivia.toMutableList())
                }
                Status.ERROR -> {
                    triviaResult.message?.let { _errorMessage.emit(it) }
                }
            }
        }
    }
}