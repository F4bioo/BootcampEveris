package fbo.costa.bootcampeveris.repository

import fbo.costa.bootcampeveris.util.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryChallenge9 {
    fun calculate(input: String): Flow<DataState<String>> = flow {
        try {
            val n1 = input.substring(0, 1).toInt()
            val n2 = input.substring(2, 3).toInt()

            val result = when {
                n1 == n2 -> n1 * n2
                Character.isUpperCase(input[1]) -> n2 - n1
                else -> n1 + n2
            }.toString()

            // Each value emit the result the list can be large
            emit(DataState.Success(result))

        } catch (e: Exception) {
            // If an error occurs send a message
            emit(DataState.Error("Exception: ${e.message}"))
        }
    }
}
