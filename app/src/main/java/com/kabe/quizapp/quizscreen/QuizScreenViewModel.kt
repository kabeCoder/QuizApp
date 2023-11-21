package com.kabe.quizapp.quizscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabe.quizapp.data.base.Status
import com.kabe.quizapp.data.repository.trivia.TriviaRepository
import com.kabe.quizapp.domain.Trivia
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _responseCode = MutableSharedFlow<Int>()
    val responseCode: SharedFlow<Int> = _responseCode.asSharedFlow()

    private val _loadingStateTrivia = MutableStateFlow(false)
    val loadingStateTrivia: StateFlow<Boolean> = _loadingStateTrivia

    private val _loadingStateCode = MutableStateFlow(false)
    val loadingStateCode: StateFlow<Boolean> = _loadingStateCode



    suspend fun getTrivia(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ) {
        _loadingStateTrivia.emit(true)
        viewModelScope.launch {
            val triviaResult = triviaRepository.getTrivia(amount, category, difficulty, type)
            when (triviaResult.status) {
                Status.SUCCESS -> triviaResult.data?.let { trivia ->
                    _loadingStateTrivia.emit(false)
                    _trivia.emit(trivia.toMutableList())
                }

                Status.ERROR -> {
                    _loadingStateTrivia.emit(false)
                    triviaResult.message?.let { _errorMessage.emit(it) }
                }
            }
        }
    }

   suspend fun getResponseCode(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ) {
       _loadingStateCode.emit(true)
        viewModelScope.launch {
            val triviaResult = triviaRepository.getResponseCode(amount, category, difficulty, type)
            when (triviaResult.status) {
                Status.SUCCESS -> triviaResult.data?.let { trivia ->
                    _loadingStateCode.emit(false)
                    _responseCode.emit(trivia)
                }

                Status.ERROR -> {
                    _loadingStateCode.emit(false)
                    triviaResult.message?.let { _errorMessage.emit(it) }
                }
            }
        }
    }
}