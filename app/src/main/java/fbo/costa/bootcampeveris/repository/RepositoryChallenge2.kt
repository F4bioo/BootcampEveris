package fbo.costa.bootcampeveris.repository

import fbo.costa.bootcampeveris.util.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryChallenge2 {
    fun calculate(valueX: String): Flow<DataState<String>> = flow {
        try {
            val result = StringBuilder()

            // Step 2 to get only the odd numbers
            for (i in 1..valueX.toInt() step 2) {
                result.append("$i\n")
            }

            // If all right Emit the values
            emit(DataState.Success(result.toString()))

        } catch (e: Exception) {
            // If an error occurs send a message
            emit(DataState.Error("Exception: ${e.message}"))
        }
    }
}
