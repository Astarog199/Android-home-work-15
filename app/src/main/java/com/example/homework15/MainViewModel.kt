package com.example.homework15

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework15.Data.DictionaryDao
import com.example.homework15.Data.Word
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val dictionaryDao: DictionaryDao) : ViewModel() {

    val allWord = this.dictionaryDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun add(word: String) {
        viewModelScope.launch {
            for (i in allWord.value) {
                if (word.equals(i.word)) {
                    val updateWord = i.copy(
                        counter = i.counter + 1
                    )
                    dictionaryDao.update(updateWord)
                    return@launch
                }
            }
            dictionaryDao.insert(Word(word = word, counter = 1))
        }
    }

    fun delete() {
        viewModelScope.launch {
            dictionaryDao.delete()
        }
    }
}




