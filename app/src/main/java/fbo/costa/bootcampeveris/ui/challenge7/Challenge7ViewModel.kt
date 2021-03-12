package fbo.costa.bootcampeveris.ui.challenge7

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fbo.costa.bootcampeveris.repository.RepositoryChallenge7
import fbo.costa.bootcampeveris.util.state.DataState
import fbo.costa.bootcampeveris.util.state.StateEventChallenge
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Challenge7ViewModel
@Inject constructor(
    private val repository: RepositoryChallenge7
) : ViewModel() {

    private val _challengeEvent = MutableLiveData<DataState<String>>()
    val challengeEvent: LiveData<DataState<String>>
        get() = _challengeEvent

    private val list = arrayListOf<Int>()

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

    fun setList(value: Int) {
        list.add(value)
    }
}
