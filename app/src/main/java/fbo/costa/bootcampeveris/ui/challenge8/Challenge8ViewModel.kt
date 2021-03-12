package fbo.costa.bootcampeveris.ui.challenge8

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fbo.costa.bootcampeveris.repository.RepositoryChallenge8
import fbo.costa.bootcampeveris.util.state.DataState
import fbo.costa.bootcampeveris.util.state.StateEventChallenge
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Challenge8ViewModel
@Inject constructor(
    private val repository: RepositoryChallenge8
) : ViewModel() {

    private val _challengeEvent = MutableLiveData<DataState<String>>()
    val challengeEvent: LiveData<DataState<String>>
        get() = _challengeEvent

    private val list = arrayListOf<String>()

    fun setStateEvent(stateEvent: StateEventChallenge) {
        if (list.size == 0) return

        viewModelScope.launch {
            when (stateEvent) {
                is StateEventChallenge.StateEvent -> {
                    repository.calculate(list).collect { _dataState ->
                        _challengeEvent.value = _dataState
                    }
                }
            }
        }
        list.clear()
    }

    fun setList(value: String) {
        list.add(value)
    }
}
