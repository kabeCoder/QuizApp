package com.kabe.quizapp.ui.presentation.quizscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabe.quizapp.data.base.Status
import com.kabe.quizapp.data.repository.trivia.TriviaRepository
import com.kabe.quizapp.domain.Trivia
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class QuizScreenViewModel @Inject constructor(
    private val triviaRepository: TriviaRepository
) : ViewModel() {

    private val _errorMessage = MutableSharedFlow<String?>()
    val errorMessage: SharedFlow<String?> = _errorMessage.asSharedFlow()

   private val triviaList = mutableListOf<Trivia>()
   private val _trivia = MutableSharedFlow<MutableList<Trivia>>()
    val trivia: SharedFlow<List<Trivia>> = _trivia.asSharedFlow()


    fun getTrivia(amount: Int, category: Int, difficulty: String, type: String) {
        viewModelScope.launch {
            val triviaResult = triviaRepository.getTrivia(amount,category,difficulty,type)
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