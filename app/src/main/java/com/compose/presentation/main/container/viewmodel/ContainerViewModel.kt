package com.compose.presentation.main.container.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.domain.provider.ResourceProvider
import com.compose.domain.usecase.GetCharacterUseCase
import com.compose.presentation.main.navigation.NavArg
import com.compose.presentation.main.navigation.findArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContainerViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val resourceProvider: ResourceProvider,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableLiveData<ContainerState>()
    val state: LiveData<ContainerState> = _state

    init {
        getCharacter()
    }

    private fun getCharacter() = viewModelScope.launch {
        savedStateHandle.findArg<Int>(NavArg.CharacterId).let { characterId ->
            getCharacterUseCase.invoke(characterId)
                .catch { exception ->
                    _state.value.apply {
                        ContainerState.Loading(isLoading = false)
                        ContainerState.Error(message = "")
                    }
                }
                .collect { response ->
                    if (response.isSuccessful()) {
                        response.data?.let { character ->
                            _state.value = ContainerState.Loading(isLoading = false)
                            _state.value = ContainerState.Data(character = character)
                        }
                    } else {
                        _state.value = ContainerState.Loading(isLoading = false)
                        _state.value = ContainerState.Error(
                            message = response.details
                                ?: resourceProvider.getErrorGettingCharacterLabel()
                        )
                    }
                }
        }
    }
}
