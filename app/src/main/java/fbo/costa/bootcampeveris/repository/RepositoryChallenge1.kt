package fbo.costa.bootcampeveris.repository

import fbo.costa.bootcampeveris.util.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryChallenge1 {
    fun calculate(valueA: String, valueB: String): Flow<DataState<Int>> = flow {
        try {
            // Add the received values
            val result = valueA.toInt().plus(valueB.toInt())

            // If all right Emit the values
            emit(DataState.Success(result))

        } catch (e: Exception) {
            // If an error occurs send a message
            emit(DataState.Error("Exception: ${e.message}"))
        }
    }
}
