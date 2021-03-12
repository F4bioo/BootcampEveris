package fbo.costa.bootcampeveris.ui.challenge3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fbo.costa.bootcampeveris.repository.RepositoryChallenge3
import fbo.costa.bootcampeveris.util.state.DataState
import fbo.costa.bootcampeveris.util.state.StateEventChallenge
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Challenge3ViewModel
@Inject constructor(
    private val repository: RepositoryChallenge3
) : ViewModel() {

    private val _challengeEvent = MutableLiveData<DataState<String>>()
    val challengeEvent: LiveData<DataState<String>>
        get() = _challengeEvent


    fun setStateEvent(valueN: String, stateEvent: StateEventChallenge) {
        viewModelScope.launch {
            when (stateEvent) {
                is StateEventChallenge.StateEvent -> {
                    repository.calculate(valueN).collect { _dataState ->
                        _challengeEvent.value = _dataState
                    }
                }
            }
        }
    }
}
