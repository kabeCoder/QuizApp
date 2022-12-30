package com.kabe.quizapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.kabe.quizapp.data.database.dao.TriviaDao
import com.kabe.quizapp.domain.Trivia
import com.kabe.quizapp.util.TriviaConverter

@Database(
    entities = [Trivia::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TriviaConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun triviaDao(): TriviaDao

}