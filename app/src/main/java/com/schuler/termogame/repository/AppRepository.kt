package com.schuler.termogame.repository

import com.schuler.termogame.data.AppDatabaseDao
import com.schuler.termogame.model.GameData
import com.schuler.termogame.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val appDatabaseDao: AppDatabaseDao
){
    //    Word Table

    suspend fun getWordsByName(name: String) = appDatabaseDao.getWordsByName(name)

    suspend fun getWordsById(id: String) = appDatabaseDao.getWordsById(id)
    suspend fun insertW(word: Word) = appDatabaseDao.insertWord(word)
    suspend fun update(word: Word) = appDatabaseDao.updateWord(word)

    fun getRandomWord(tf: Int) = appDatabaseDao
        .getRandomWord(tf)
        .flowOn(Dispatchers.IO)
        .conflate()


    fun getWordsByNoAccentName(noAccentName: String) = appDatabaseDao
        .getWordsByNoAccentName(noAccentName)
        .flowOn(Dispatchers.IO)
        .conflate()


    fun getAllWords(): Flow<List<Word>> = appDatabaseDao
        .getWords()
        .flowOn(Dispatchers.IO)
        .conflate()


//  GameData Table
    suspend fun getGameDataByDificulty(difficulty: Int) = appDatabaseDao.getGameDataByDifficulty(difficulty)
    suspend fun insertGameData(gameData: GameData) = appDatabaseDao.insertGameData(gameData)

    suspend fun updateRights1(rights1: Int, difficulty: Int) = appDatabaseDao.updateRights1(rights1, difficulty)
    suspend fun updateRights2(rights2: Int, difficulty: Int) = appDatabaseDao.updateRights1(rights2, difficulty)
    suspend fun updateRights3(rights3: Int, difficulty: Int) = appDatabaseDao.updateRights1(rights3, difficulty)
    suspend fun updateRights4(rights4: Int, difficulty: Int) = appDatabaseDao.updateRights1(rights4, difficulty)
    suspend fun updateRights5(rights5: Int, difficulty: Int) = appDatabaseDao.updateRights1(rights5, difficulty)
    suspend fun updateRights6(rights6: Int, difficulty: Int) = appDatabaseDao.updateRights1(rights6, difficulty)




    suspend fun updateWrongs(wrongs: Int, difficulty: Int) = appDatabaseDao.updateRights1(wrongs, difficulty)



    fun getAllGameData() = appDatabaseDao.getGameData().flowOn(Dispatchers.IO).conflate()
}