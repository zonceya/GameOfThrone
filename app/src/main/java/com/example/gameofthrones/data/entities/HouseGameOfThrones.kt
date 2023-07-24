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
    val titles: List<String>,
    val seats: List<String>,
    val currentLord: String,
    val heir: String,
    val overlord: String,
    val founded: String,
    val founder: String,
    val diedOut: String,
    val ancestralWeapons: List<String>,
    val cadetBranches: List<String>,
    val swornMembers: List<String>)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

