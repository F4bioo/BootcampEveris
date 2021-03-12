package fbo.costa.bootcampeveris.ui.challenge1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fbo.costa.bootcampeveris.repository.RepositoryChallenge1
import fbo.costa.bootcampeveris.util.state.DataState
import fbo.costa.bootcampeveris.util.state.StateEventChallenge
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Challenge1ViewModel
@Inject constructor(
    private val repository: RepositoryChallenge1
) : ViewModel() {

    private val _challengeEvent = MutableLiveData<DataState<Int>>()
    val challengeEvent: LiveData<DataState<Int>>
        get() = _challengeEvent

    fun setStateEvent(valueA: String, valueB: String, stateEvent: StateEventChallenge) {
        viewModelScope.launch {
            when (stateEvent) {
                is StateEventChallenge.StateEvent -> {
                    repository.calculate(valueA, valueB).collect { _dataState ->
                        _challengeEvent.value = _dataState
                    }
                }
            }
        }
    }
}
