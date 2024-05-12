package com.compose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_favorite")
data class CharacterFavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "specie") val specie: String?,
    @ColumnInfo(name = "image") val image: String?,
)
