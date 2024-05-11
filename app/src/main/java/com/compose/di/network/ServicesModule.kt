/*
 * ServicesModule.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2025. All rights reserved.
 */
package com.compose.di.network

import com.compose.data.network.service.CharacterServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {
    @Provides
    fun provideRecipesServices(retrofit: Retrofit): CharacterServices =
        retrofit.create(CharacterServices::class.java)
}
