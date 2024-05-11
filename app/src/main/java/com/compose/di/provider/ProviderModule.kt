/*
 * RepositoryModule.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.di.provider

import com.compose.data.provider.ResourceProviderImpl
import com.compose.domain.provider.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProviderModule {
    @Singleton
    @Binds
    abstract fun provideResourceProvider(containerResourceProviderImpl: ResourceProviderImpl): ResourceProvider
}
