package com.schuler.termogame.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "game_data_table")
data class GameData(
    @PrimaryKey
    @ColumnInfo(name = "game_data_difficulty")
    var difficulty: Int = 2,

    @ColumnInfo(name = "game_data_rights1")
    var rights1: Int  = 0 ,

    @ColumnInfo(name = "game_data_rights2")
    var rights2: Int  = 0 ,

    @ColumnInfo(name = "game_data_rights3")
    var rights3: Int  = 0 ,

    @ColumnInfo(name = "game_data_rights4")
    var rights4: Int  = 0 ,

    @ColumnInfo(name = "game_data_rights5")
    var rights5: Int  = 0 ,

    @ColumnInfo(name = "game_data_rights6")
    var rights6: Int  = 0 ,

    @ColumnInfo(name = "game_data_wrongs")
    var wrongs: Int  = 0,

)