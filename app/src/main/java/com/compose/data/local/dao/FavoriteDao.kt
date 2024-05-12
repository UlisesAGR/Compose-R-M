package com.compose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.compose.data.local.entity.CharacterFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM character_favorite")
    fun getFavorites(): Flow<List<CharacterFavoriteEntity>>

    @Query("SELECT * FROM character_favorite WHERE id=:id")
    suspend fun existInFavorites(id: Int): CharacterFavoriteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(character: CharacterFavoriteEntity)

    @Delete
    suspend fun deleteToFavorite(character: CharacterFavoriteEntity)
}
