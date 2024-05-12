package com.compose.presentation.main.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.data.network.utils.parseException
import com.compose.domain.provider.ResourceProvider
import com.compose.domain.usecase.favorite.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _state = MutableLiveData<FavoriteState>()
    val state: LiveData<FavoriteState> = _state

    init {
        getFavorites()
    }

    private fun getFavorites() =
        viewModelScope.launch {
            getFavoritesUseCase.invoke()
                .catch { exception ->
                    _state.value = FavoriteState.Loading(isLoading = false)
                    _state.value = FavoriteState.Error(
                        message = resourceProvider.parseError(exception.parseException().error)
                    )
                }
                .collect { list ->
                    _state.value = FavoriteState.Loading(isLoading = false)
                    _state.value = FavoriteState.Data(characters = list)
                }
        }
}
