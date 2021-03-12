package fbo.costa.bootcampeveris.repository

import fbo.costa.bootcampeveris.util.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryChallenge3 {
    fun calculate(valueN: String): Flow<DataState<String>> = flow {
        try {
            val result = StringBuilder()
            for (i in 1..valueN.toInt()) {
                result.append("$i ${i * i} ${i * i * i}\n")
            }

            // If all right Emit the values
            emit(DataState.Success(result.toString()))

        } catch (e: Exception) {
            // If an error occurs send a message
            emit(DataState.Error("Exception: ${e.message}"))
        }
    }
}
