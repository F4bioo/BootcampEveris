package fbo.costa.bootcampeveris.ui.challenge9

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fbo.costa.bootcampeveris.repository.RepositoryChallenge9
import fbo.costa.bootcampeveris.util.state.DataState
import fbo.costa.bootcampeveris.util.state.StateEventChallenge
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Challenge9ViewModel
@Inject constructor(
    private val repository: RepositoryChallenge9
) : ViewModel() {

    private val _challengeEvent = MutableLiveData<DataState<String>>()
    val challengeEvent: LiveData<DataState<String>>
        get() = _challengeEvent

    fun setStateEvent(input: String, stateEvent: StateEventChallenge) {
        viewModelScope.launch {
            when (stateEvent) {
                is StateEventChallenge.StateEvent -> {
                    repository.calculate(input).collect { _dataState ->
                        _challengeEvent.value = _dataState
                    }
                }
            }
        }
    }
}
