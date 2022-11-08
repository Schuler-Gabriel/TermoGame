package com.schuler.termogame.screens.home

import android.app.Application
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.schuler.termogame.dataStore.readInt
import com.schuler.termogame.dataStore.writeInt
import com.schuler.termogame.model.GameData
import com.schuler.termogame.model.Word
import com.schuler.termogame.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: AppRepository, private val appContext: Application): AndroidViewModel(appContext) {

    // mutable variables
    private val _gameDataList = MutableStateFlow<List<GameData>>(emptyList())
    val gameDataList = _gameDataList.asStateFlow()

    private val _difficulty = MutableStateFlow<Int>(0)
    val difficulty = _difficulty.asStateFlow()

    private val _randomWord = MutableStateFlow<Word>(Word("","",0,0))
    val randomWord = _randomWord.asStateFlow()

    private val _searchedWordExistes = MutableStateFlow<Boolean>(false)
    val searchedWordExistes = _searchedWordExistes.asStateFlow()


    var activeField = mutableStateOf("0")

    var activeLine = mutableStateOf(0)

    var wordList = mutableStateListOf(
            mutableStateListOf("","","","",""),
            mutableStateListOf("","","","",""),
            mutableStateListOf("","","","",""),
            mutableStateListOf("","","","",""),
            mutableStateListOf("","","","",""),
            mutableStateListOf("","","","",""),
        )

    var matchingLetterState = mutableStateListOf(
            mutableStateListOf(0,0,0,0,0),
            mutableStateListOf(0,0,0,0,0),
            mutableStateListOf(0,0,0,0,0),
            mutableStateListOf(0,0,0,0,0),
            mutableStateListOf(0,0,0,0,0),
            mutableStateListOf(0,0,0,0,0),
        )





    //Data Store

    companion object{
        const val KEY_NAME = "difficulty"
    }

    private val getDifficulty = appContext.readInt(KEY_NAME)

    fun saveDifficulty(diff: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeInt(KEY_NAME, diff)
            _difficulty.value = diff

            when (_difficulty.value) {
                1 -> {
                    repository.getRandomWord(100000).distinctUntilChanged()
                        .collect{word ->
                            println(word)
                            if(word == null){
                                Log.d("Empty", ": Difficulty Empty")
                            } else {
                                _randomWord.value = word
                            }
                        }
                }
                2 -> {
                    repository.getRandomWord(100000).distinctUntilChanged()
                        .collect{word ->
                            println(word)
                            if(word == null){
                                Log.d("Empty", ": Difficulty Empty")
                            } else {
                                _randomWord.value = word
                            }
                        }
                }
                3 ->{
                    repository.getRandomWord(100000).distinctUntilChanged()
                        .collect{word ->
                            println(word)
                            if(word == null){
                                Log.d("Empty", ": Difficulty Empty")
                            } else {
                                _randomWord.value = word
                            }
                        }
                }
            }
        }
    }



    //When criate
    init {
        viewModelScope.launch(Dispatchers.IO){
            getDifficulty.distinctUntilChanged()
                .collect {diff ->
                    if(diff == null){
                        Log.d("Empty", ": Difficulty Empty")
                    } else {
                        if(diff == 1 || diff == 2 || diff == 3){
                            _difficulty.value = diff

                        } else {
                            appContext.writeInt("difficulty", 2)
                            _difficulty.value = 2
                        }

                        when (diff) {
                            1 -> {
                                repository.getRandomWord(100000).distinctUntilChanged()
                                    .collect{word ->
                                        println(word)
                                        if(word == null){
                                            Log.d("Empty", ": Difficulty Empty")
                                        } else {
                                            _randomWord.value = word
                                        }
                                    }
                            }
                            2 -> {
                                repository.getRandomWord(100000).distinctUntilChanged()
                                    .collect{word ->
                                        println(word)
                                        if(word == null){
                                            Log.d("Empty", ": Difficulty Empty")
                                        } else {
                                            _randomWord.value = word
                                        }
                                    }
                            }
                            3 ->{
                                repository.getRandomWord(100000).distinctUntilChanged()
                                    .collect{word ->
                                        println(word)
                                        if(word == null){
                                            Log.d("Empty", ": Difficulty Empty")
                                        } else {
                                            _randomWord.value = word
                                        }
                                    }
                            }
                        }
                    }
                }
        }
    }

    //Functions

    fun reset(){
        for (l in 0..5){
            for (j in 0..4){
                wordList[l][j] = ""
                matchingLetterState[l][j]= 0
            }
        }
        changeRandomWord()
        activeField.value = "0"
        activeLine.value = 0
    }


    fun formingWord(list: List<String>): String{
        var word = ""
        for (i in list){
            word += i
        }
        return word
    }
    suspend fun onSendWord(){

    }


    fun wordCheckHandler(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getWordsByNoAccentName(formingWord(wordList[activeLine.value])).distinctUntilChanged()
                .collect{word ->
                    if(word == null){
                        println("nao existe a palavra: " + _searchedWordExistes.value)
                    } else {
                        println(word)
                            if (formingWord(wordList[activeLine.value]) == _randomWord.value.noAccentName){
                                reset()
                            } else {
                                if (activeLine.value < 5) {
                                    for (i in 0..4){
                                        if(formingWord(wordList[activeLine.value])[i] in _randomWord.value.noAccentName){
                                            if(formingWord(wordList[activeLine.value])[i] == _randomWord.value.noAccentName[i]){
                                                matchingLetterState[activeLine.value][i] = 2
                                            } else {
                                                matchingLetterState[activeLine.value][i] = 1
                                            }
                                        }
                                    }
                                    activeLine.value++
                                    activeField.value = "0"
                                } else {
                                    reset()
                                    println("esgotou as 6 tentativas")
                                }
                            }

                    }
                }
        }
    }


    fun updateRights(rights: Int,  tryNumer: Int) {
        viewModelScope.launch {
            when (tryNumer) {
                1 -> repository.updateRights1(rights, _difficulty.value)
                2 -> repository.updateRights2(rights, _difficulty.value)
                3 -> repository.updateRights3(rights, _difficulty.value)
                4 -> repository.updateRights4(rights, _difficulty.value)
                5 -> repository.updateRights5(rights, _difficulty.value)
                6 -> repository.updateRights6(rights, _difficulty.value)
            }
        }
    }

   fun updateWrongs(wrongs: Int) {
       viewModelScope.launch {
           repository.updateWrongs(wrongs, _difficulty.value)
       }
   }

  private suspend fun searchWordByName(name: String){

    repository.getWordsByNoAccentName(name).distinctUntilChanged()
        .collect{word ->
            if(word == null){
                _searchedWordExistes.value = false
                println("dentro do search word (null): " + _searchedWordExistes.value)
            } else {
                println(word)
                _searchedWordExistes.value = word.noAccentName == name
                println("dentro do search word: " + _searchedWordExistes.value)
            }
        }
  }

    fun changeRandomWord() {
        viewModelScope.launch(Dispatchers.IO){
            when (_difficulty.value) {
                1 -> {
                    repository.getRandomWord(100000).distinctUntilChanged()
                        .collect{word ->
                            println(word)
                            if(word == null){
                                Log.d("Empty", ": Difficulty Empty")
                            } else {
                                _randomWord.value = word
                            }
                        }
                }
                2 -> {
                    repository.getRandomWord(100000).distinctUntilChanged()
                        .collect{word ->
                            println(word)
                            if(word == null){
                                Log.d("Empty", ": Difficulty Empty")
                            } else {
                                _randomWord.value = word
                            }
                        }
                }
                3 ->{
                    repository.getRandomWord(100000).distinctUntilChanged()
                        .collect{word ->
                            println(word)
                            if(word == null){
                                Log.d("Empty", ": Difficulty Empty")
                            } else {
                                _randomWord.value = word
                            }
                        }
                }
            }
        }

    }
}


