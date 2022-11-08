package com.schuler.termogame.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "words_table")
data class Word(
    @ColumnInfo(name = "word_name")
    val name: String,

    @ColumnInfo(name = "word_no_accent_name")
    val noAccentName: String,

    @ColumnInfo(name = "word_tf")
    var tf: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int,
)
