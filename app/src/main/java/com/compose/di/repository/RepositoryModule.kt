/*
 * RepositoryModule.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.di.repository

import com.compose.data.repository.CharacterRepositoryImpl
import com.compose.data.repository.FavoriteRepositoryImpl
import com.compose.domain.repository.CharacterRepository
import com.compose.domain.repository.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Singleton
    @Binds
    abstract fun provideFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository
}
