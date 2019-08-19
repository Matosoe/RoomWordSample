package br.com.matosoe.roomwordsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.matosoe.roomwordsample.dao.WordRoomDatabase
import br.com.matosoe.roomwordsample.model.Word
import br.com.matosoe.roomwordsample.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository
    val allWords: LiveData<List<Word>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}