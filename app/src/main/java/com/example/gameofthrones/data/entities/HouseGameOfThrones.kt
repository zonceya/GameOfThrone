package com.example.gameofthrones.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "houses")
data class HouseGameOfThrones(
    val url: String,
    val name: String,
    val region: String,
    val coatOfArms: String,
    val words: String,
    val currentLord: String,
    val heir: String,
    val overlord: String,
    val founded: String,
    val founder: String,
    val diedOut: String)

{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

