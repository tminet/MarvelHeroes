package tmidev.marvelheroes.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.core.domain.model.Comic
import tmidev.core.domain.model.Event
import tmidev.core.domain.usecase.GetCharacterCategoriesUseCase
import tmidev.core.domain.usecase.base.ResultStatus
import tmidev.marvelheroes.R
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun getCharacterCategories(characterId: Int) = viewModelScope.launch {
        getCharacterCategoriesUseCase(
            GetCharacterCategoriesUseCase.GetCharacterCategoriesParams(characterId)
        ).watchStats()
    }

    private suspend fun Flow<ResultStatus<Pair<List<Comic>, List<Event>>>>.watchStats() {
        collectLatest { status ->
            when (status) {
                is ResultStatus.Loading -> _uiState.value = UiState.Loading
                is ResultStatus.Success -> {
                    val detailParentList = mutableListOf<DetailParentVE>()

                    val comics = status.data.first
                    if (comics.isNotEmpty()) comics.map {
                        DetailChildVE(it.id, it.imageUrl)
                    }.also {
                        detailParentList.add(
                            DetailParentVE(R.string.detailsCategoryComics, it)
                        )
                    }

                    val events = status.data.second
                    if (events.isNotEmpty()) events.map {
                        DetailChildVE(it.id, it.imageUrl)
                    }.also {
                        detailParentList.add(
                            DetailParentVE(R.string.detailsCategoryEvents, it)
                        )
                    }

                    _uiState.value =
                        if (detailParentList.isNotEmpty()) UiState.Success(detailParentList)
                        else UiState.Empty
                }
                is ResultStatus.Error -> _uiState.value = UiState.Error
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val detailParentList: List<DetailParentVE>) : UiState()
        object Error : UiState()
        object Empty : UiState()
    }
}