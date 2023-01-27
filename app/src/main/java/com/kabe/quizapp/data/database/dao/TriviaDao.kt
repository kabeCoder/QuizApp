package com.kabe.quizapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kabe.quizapp.domain.Trivia

@Dao
abstract class TriviaDao {

    @Query("SELECT * FROM trivia")
    abstract suspend fun getAllCurrentTrivia(): List<Trivia>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCurrentTrivia(trivia: List<Trivia>)

    @Query("DELETE FROM trivia")
    abstract suspend fun deleteAllTrivia()
}