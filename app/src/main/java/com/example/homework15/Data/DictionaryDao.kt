package com.example.homework15.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {
@Query("SELECT * FROM Dictionary")
fun getAll(): Flow<List<Dictionary>>

@Insert (entity = Dictionary::class)
suspend fun insert(word: Word)


@Query("DELETE FROM Dictionary")
suspend fun delete()

@Update
suspend fun update(updateWord: Dictionary)
}