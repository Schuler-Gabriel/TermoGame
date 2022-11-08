package com.schuler.termogame.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.schuler.termogame.model.GameData
import com.schuler.termogame.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDatabaseDao {

//    Word Table
    @Query("SELECT * from words_table")
    fun getWords(): Flow<List<Word>>

    @Query("SELECT * from words_table where id =:id")
    suspend fun getWordsById(id: String): Word

    @Query("SELECT * from words_table where word_name =:name")
    suspend fun getWordsByName(name: String): Word

    @Query("SELECT * from words_table where word_name =:noAccentName")
    fun getWordsByNoAccentName(noAccentName: String): Flow<Word>

    @Query("SELECT * FROM words_table WHERE word_tf >:tf ORDER BY RANDOM() LIMIT 1")
    fun getRandomWord(tf: Int): Flow<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWord(word: Word)

    @Query("DELETE from words_table")
    suspend fun deleteAllWords()

    @Delete
    suspend fun deleteWord(word: Word)

//    GameData Table

    @Query("SELECT * from game_data_table")
    fun getGameData(): Flow<List<GameData>>

    @Query("SELECT * from game_data_table where game_data_difficulty =:difficulty")
    suspend fun getGameDataByDifficulty(difficulty: Int): GameData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameData(gameData: GameData)

    @Query("UPDATE game_data_table SET game_data_rights1=:rights1 WHERE game_data_difficulty=:difficulty")
    suspend fun updateRights1(rights1: Int, difficulty: Int)

    @Query("UPDATE game_data_table SET game_data_rights2=:rights2 WHERE game_data_difficulty=:difficulty")
    suspend fun updateRights2(rights2: Int, difficulty: Int)

    @Query("UPDATE game_data_table SET game_data_rights3=:rights3 WHERE game_data_difficulty=:difficulty")
    suspend fun updateRights3(rights3: Int, difficulty: Int)

    @Query("UPDATE game_data_table SET game_data_rights4=:rights4 WHERE game_data_difficulty=:difficulty")
    suspend fun updateRights4(rights4: Int, difficulty: Int)

    @Query("UPDATE game_data_table SET game_data_rights5=:rights5 WHERE game_data_difficulty=:difficulty")
    suspend fun updateRights5(rights5: Int, difficulty: Int)

    @Query("UPDATE game_data_table SET game_data_rights6=:rights6 WHERE game_data_difficulty=:difficulty")
    suspend fun updateRights6(rights6: Int, difficulty: Int)

    @Query("UPDATE game_data_table SET game_data_wrongs=:wrongs WHERE game_data_difficulty=:difficulty")
    suspend fun updateWrongs(wrongs: Int, difficulty: Int)

    @Query("DELETE from game_data_table")
    suspend fun deleteAllGameData()


}
