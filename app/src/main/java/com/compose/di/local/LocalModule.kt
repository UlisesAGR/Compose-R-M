package com.compose.di.local

import android.content.Context
import androidx.room.Room
import com.compose.BuildConfig.DATABASE_NAME
import com.compose.data.local.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Module
    @InstallIn(SingletonComponent::class)
    object RoomModule {

        @Singleton
        @Provides
        fun provideRoom(@ApplicationContext context: Context) =
            Room.databaseBuilder(
                context,
                Database::class.java,
                DATABASE_NAME,
            ).fallbackToDestructiveMigration().build()

        @Singleton
        @Provides
        fun provideCharacterDao(db: Database) = db.characterDao()

        @Singleton
        @Provides
        fun provideRemoteKeysDao(db: Database) = db.remoteKeysDao()

        @Singleton
        @Provides
        fun provideRemoteFavoriteDao(db: Database) = db.remoteFavoriteDao()
    }
}
