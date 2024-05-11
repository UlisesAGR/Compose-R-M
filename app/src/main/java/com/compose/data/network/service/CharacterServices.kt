/*
 * RecipesServices.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.data.network.service

import com.compose.BuildConfig.CHARACTER
import com.compose.data.network.response.CharacterResponse
import com.compose.data.network.response.CharactersDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterServices {
    @GET(CHARACTER)
    suspend fun getCharacters(
        @Query("page") page: Int,
    ): CharactersDataResponse

    @GET("$CHARACTER/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
    ): Response<CharacterResponse>
}
