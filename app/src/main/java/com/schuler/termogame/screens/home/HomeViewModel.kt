package com.schuler.termogame.screens.home

import android.app.Application
import android.util.Log
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.input.key.Key.Companion.G
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.schuler.termogame.dataStore.readInt
import com.schuler.termogame.dataStore.readString
import com.schuler.termogame.dataStore.writeInt
import com.schuler.termogame.dataStore.writeString
import com.schuler.termogame.model.GameData
import com.schuler.termogame.model.Word
import com.schuler.termogame.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

    private val _wins = MutableStateFlow<List<Float>>(listOf(0f,0f,0f,0f))
    val wins = _wins.asStateFlow()

    private val _losses = MutableStateFlow<List<Float>>(listOf(0f,0f,0f,0f))
    val losses = _losses.asStateFlow()

    private val _randomWord = MutableStateFlow<Word>(Word("","",0,0))
    val randomWord = _randomWord.asStateFlow()

    private val _searchedWordExistes = MutableStateFlow<Boolean>(false)

    var wordDialogRight = mutableStateOf(false)

    var wordDialogWrong = mutableStateOf(false)

    var activeField = mutableStateOf(0)

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
    var alphabet = mutableStateMapOf<String, Int>(
        "A" to 10,
        "B" to 10,
        "C" to 10,
        "D" to 10,
        "E" to 10,
        "F" to 10,
        "G" to 10,
        "H" to 10,
        "I" to 10,
        "J" to 10,
        "K" to 10,
        "L" to 10,
        "M" to 10,
        "N" to 10,
        "O" to 10,
        "P" to 10,
        "Q" to 10,
        "R" to 10,
        "S" to 10,
        "T" to 10,
        "U" to 10,
        "V" to 10,
        "W" to 10,
        "X" to 10,
        "Y" to 10,
        "Z" to 10,
    )





    //Data Store

    companion object{
        const val KEY_DIFFICULTY = "difficulty"
        const val KEY_WINS = "wins"
        const val KEY_LOSSES = "losses"
    }

    private val getWins = appContext.readString(KEY_WINS)
    fun saveWins(wins: List<Int>){
        viewModelScope.launch(Dispatchers.IO){
            appContext.writeString(KEY_WINS ,wins.joinToString(separator = "/"))
        }
    }

    private val getLosses = appContext.readString(KEY_LOSSES)
    fun saveLosses(losses: List<Int>){
        viewModelScope.launch(Dispatchers.IO){
            appContext.writeString(KEY_WINS ,losses.joinToString(separator = "/"))
        }
    }

    private val getDifficulty = appContext.readInt(KEY_DIFFICULTY)

    private fun initWinsAndLosses(){
        viewModelScope.launch(Dispatchers.IO){
            getWins.distinctUntilChanged().collect { wins ->
                if (wins == null) {
                    Log.d("Empty", ": Difficulty Empty")
                } else {
                    if (wins.length >= 7) {
                        _wins.value = wins.split("/").map {
                            try {
                                it.toFloat()
                            } catch (e: NumberFormatException) {
                                0f
                            }
                        }
                    } else {
                        appContext.writeString(KEY_WINS, "0/0/0/0")
                        _wins.value = listOf(0f, 0f, 0f, 0f)
                    }
                }
            }
            getLosses.distinctUntilChanged().collect { losses ->
                if (losses == null) {
                    Log.d("Empty", ": Difficulty Empty")
                } else {
                    if (losses.length >= 7) {
                        _losses.value = losses.split("/").map {
                            try {
                                it.toFloat()
                            } catch (e: NumberFormatException) {
                                0f
                            }
                        }
                    } else {
                        appContext.writeString(KEY_LOSSES, "0/0/0/0")
                        _losses.value = listOf(0f, 0f, 0f, 0f)
                    }
                }
            }
        }
    }

    fun saveDifficulty(diff: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeInt(KEY_DIFFICULTY, diff)
            _difficulty.value = diff
            reset()

            when (_difficulty.value) {
                1 -> {
                    repository.getRandomWord(93000000 ,97000).distinctUntilChanged()
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
                    repository.getRandomWord(450000,45000).distinctUntilChanged()
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
                    repository.getRandomWord(50000,3000).distinctUntilChanged()
                        .collect{word ->
                            println(word)
                            if(word == null){
                                Log.d("Empty", ": Difficulty Empty")
                            } else {
                                _randomWord.value = word
                            }
                        }
                }
                4 ->{
                    repository.getRandomWord(93000000,9900).distinctUntilChanged()
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

    private fun initGetDifficulty(){
        viewModelScope.launch(Dispatchers.IO){
            getDifficulty.distinctUntilChanged().collect {diff ->
                println("diff: $diff")
                if(diff == null){
                    Log.d("Empty", ": Difficulty Empty")
                } else {
                    if(diff == 1 || diff == 2 || diff == 3 || diff == 4){
                        _difficulty.value = diff

                    } else {
                        appContext.writeInt(KEY_DIFFICULTY, 4)
                        _difficulty.value = 4
                    }
                    when (diff) {
                        1 -> {
                            repository.getRandomWord(93000000 ,97000).distinctUntilChanged()
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
                            repository.getRandomWord(450000,45000).distinctUntilChanged()
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
                            repository.getRandomWord(50000,3000).distinctUntilChanged()
                                .collect{word ->
                                    println(word)
                                    if(word == null){
                                        Log.d("Empty", ": Difficulty Empty")
                                    } else {
                                        _randomWord.value = word
                                    }
                                }
                        }
                        4 ->{
                            repository.getRandomWord(93000000,9900).distinctUntilChanged()
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



    //When criate
    init {
        initWinsAndLosses()
        initGetDifficulty()
    }

    //Functions

    private fun reset(){

        for (l in 0..5){
            for (j in 0..4){
                wordList[l][j] = ""
                matchingLetterState[l][j]= 0
            }
        }
        alphabet = mutableStateMapOf(
            "A" to 10,
            "B" to 10,
            "C" to 10,
            "D" to 10,
            "E" to 10,
            "F" to 10,
            "G" to 10,
            "H" to 10,
            "I" to 10,
            "J" to 10,
            "K" to 10,
            "L" to 10,
            "M" to 10,
            "N" to 10,
            "O" to 10,
            "P" to 10,
            "Q" to 10,
            "R" to 10,
            "S" to 10,
            "T" to 10,
            "U" to 10,
            "V" to 10,
            "W" to 10,
            "X" to 10,
            "Y" to 10,
            "Z" to 10,
        )
        changeRandomWord()
        activeField.value = 0
        activeLine.value = 0
        wordDialogRight.value = false
        wordDialogWrong.value = false
    }
    fun getGameData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGameData().distinctUntilChanged()
                .collect { gameData ->
                    if (gameData == null) {
                        Log.d("Empty", ": Difficulty Empty")
                    } else {
                        _gameDataList.value = gameData
                    }
                }
        }
    }


    private fun formingWord(list: List<String>): String{
        var word = ""
        for (i in list){
            word += i
        }
        return word
    }




    private fun duplicatedLetterWordList(name: String): MutableMap<String, Int> {
        var numberOfRepetitions = mutableListOf(0,0,0,0,0)
        for (i in 0..4){
            for (j in 0..4){
                if (name[i] == name[j]){
                    numberOfRepetitions[i]++
                }
            }
        }
        var list = mutableMapOf<String, Int>()
        for (i in 0..4){
            list[name[i].toString()] = numberOfRepetitions[i]
        }

        var repeatedLetters = list.keys.distinct().toList()


        var dictionary = mutableMapOf<String, Int>()
        for (i in repeatedLetters.indices){
            dictionary[repeatedLetters[i]] = list[repeatedLetters[i]]!!

        }


        return dictionary
    }

    fun wordDialogFunction(){
        reset()
    }



        fun wordCheckHandler(snackbarHostState: SnackbarHostState){
            val wordFormed = formingWord(wordList[activeLine.value])

        viewModelScope.launch(Dispatchers.IO){
            repository.getWordsByNoAccentName(wordFormed).distinctUntilChanged()
                .collect{word ->
                    if(word == null){
                            snackbarHostState.showSnackbar(
                                message = "Desculpe, essa palavra não existe em nosso banco de dados",
                                actionLabel = "OK",
                                duration = SnackbarDuration.Short
                            )
                        println("nao existe a palavra: " + _searchedWordExistes.value)
                    } else {
                        println(word)
                            if (wordFormed == _randomWord.value.noAccentName){
                                wordDialogRight.value = true
                            } else {

                                if (activeLine.value < 5) {
                                    var repetitionsOfLetters = duplicatedLetterWordList(_randomWord.value.noAccentName)

                                    for (i in 0..4){
                                        if (wordFormed[i] == _randomWord.value.noAccentName[i]){
                                            matchingLetterState[activeLine.value][i] = 2
                                            alphabet[wordFormed[i].toString()] = 2
                                            repetitionsOfLetters[wordFormed[i].toString()] = repetitionsOfLetters[wordFormed[i].toString()]!! - 1
                                        }
                                    }
                                    for (i in 0..4){
                                        if (wordFormed[i]in _randomWord.value.noAccentName){
                                           if(
                                               (repetitionsOfLetters[wordFormed[i].toString()]!! > 0) &&
                                               (matchingLetterState[activeLine.value][i] != 2)
                                           ){
                                               matchingLetterState[activeLine.value][i] = 1
                                               alphabet[wordFormed[i].toString()] = 1
                                               repetitionsOfLetters[wordFormed[i].toString()] = repetitionsOfLetters[wordFormed[i].toString()]!! - 1
                                           }
                                        } else {
                                            alphabet[wordFormed[i].toString()] = 0
                                        }
                                    }

                                    for (i in 0..4){
                                        wordList[ activeLine.value][i] = word.name[i].toString()
                                    }
                                    activeLine.value++
                                    activeField.value = 0
                                } else{
                                    wordDialogWrong.value = true
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

    private fun changeRandomWord() {
        viewModelScope.launch(Dispatchers.IO){
            when (_difficulty.value) {
                1 -> {
                    repository.getRandomWord(93000000 ,97000).distinctUntilChanged()
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
                    repository.getRandomWord(450000,45000).distinctUntilChanged()
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
                    repository.getRandomWord(50000,3000).distinctUntilChanged()
                        .collect{word ->
                            println(word)
                            if(word == null){
                                Log.d("Empty", ": Difficulty Empty")
                            } else {
                                _randomWord.value = word
                            }
                        }
                }
                4 ->{
                    repository.getRandomWord(93000000,9900).distinctUntilChanged()
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


