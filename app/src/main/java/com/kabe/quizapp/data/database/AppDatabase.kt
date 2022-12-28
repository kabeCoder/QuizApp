package com.kabe.quizapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kabe.quizapp.data.database.dao.TriviaDao
import com.kabe.quizapp.domain.Trivia

@Database(
    entities = [Trivia::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun triviaDao(): TriviaDao

}