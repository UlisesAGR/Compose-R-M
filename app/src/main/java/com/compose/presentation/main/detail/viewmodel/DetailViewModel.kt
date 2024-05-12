package com.compose.presentation.main.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.data.network.utils.parseException
import com.compose.domain.model.Character
import com.compose.domain.provider.ResourceProvider
import com.compose.domain.usecase.character.GetCharacterUseCase
import com.compose.domain.usecase.favorite.AddInFavoriteUseCase
import com.compose.domain.usecase.favorite.DeleteInFavoriteUseCase
import com.compose.domain.usecase.favorite.ExistInFavoritesUseCase
import com.compose.presentation.main.navigation.NavArg
import com.compose.presentation.main.navigation.findArg
import com.compose.ui.utils.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val existInFavoritesUseCase: ExistInFavoritesUseCase,
    private val addInFavoriteUseCase: AddInFavoriteUseCase,
    private val deleteInFavoriteUseCase: DeleteInFavoriteUseCase,
    private val resourceProvider: ResourceProvider,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableLiveData<DetailState>()
    val state: LiveData<DetailState> = _state

    private val _components = MutableLiveData<UIComponent>()
    val components: LiveData<UIComponent> = _components

    private lateinit var currentCharacter: Character

    init {
        getCharacter()
    }

    private fun getCharacter() =
        viewModelScope.launch {
            savedStateHandle.findArg<Int>(NavArg.CharacterId).let { characterId ->
                getCharacterUseCase.invoke(characterId)
                    .catch { exception ->
                        _state.value = DetailState.Loading(isLoading = false)
                        _state.value = DetailState.Error(
                            message = resourceProvider.parseError(exception.parseException().error)
                        )
                    }
                    .collect { response ->
                        if (response.isSuccessful()) {
                            response.data?.let { character ->
                                _state.value = DetailState.Loading(isLoading = false)
                                _state.value = DetailState.Data(character = character)
                                currentCharacter = character
                            }
                        } else {
                            _state.value = DetailState.Loading(isLoading = false)
                            _state.value = DetailState.Error(
                                message = response.details
                                    ?: resourceProvider.errorGetCharacterLabel()
                            )
                        }
                    }
            }
        }

    fun existInFavorites() =
        viewModelScope.launch {
            existInFavoritesUseCase.invoke(currentCharacter.id)
                .catch { exception ->
                    _state.value = DetailState.Error(
                        message = resourceProvider.parseError(exception.parseException().error)
                    )
                }
                .collect { isExistInFavorite ->
                    isExistInFavorite?.let {
                        deleteInFavorite(currentCharacter)
                    } ?: run {
                        addFavorite(currentCharacter)
                    }
                }
        }

    private fun addFavorite(character: Character) =
        viewModelScope.launch {
            addInFavoriteUseCase.invoke(character)
                .catch { exception ->
                    _state.value = DetailState.Error(
                        message = resourceProvider.parseError(exception.parseException().error)
                    )
                }
                .collect {
                    _components.value = UIComponent.Toast(
                        message = resourceProvider.addInFavoriteLabel()
                    )
                }
        }

    private fun deleteInFavorite(character: Character) =
        viewModelScope.launch {
            deleteInFavoriteUseCase.invoke(character)
                .catch { exception ->
                    _state.value = DetailState.Error(
                        message = resourceProvider.parseError(exception.parseException().error)
                    )
                }
                .collect {
                    _components.value = UIComponent.Toast(
                        message = resourceProvider.deleteInFavoriteLabel()
                    )
                }
        }
}
