package com.compose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.data.local.dao.CharacterDao
import com.compose.data.local.dao.FavoriteDao
import com.compose.data.local.dao.RemoteKeyDao
import com.compose.data.local.entity.CharacterEntity
import com.compose.data.local.entity.CharacterFavoriteEntity
import com.compose.data.local.entity.RemoteKeyEntity

@Database(
    entities = [
        CharacterEntity::class,
        RemoteKeyEntity::class,
        CharacterFavoriteEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class Database : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeysDao(): RemoteKeyDao
    abstract fun remoteFavoriteDao(): FavoriteDao
}
