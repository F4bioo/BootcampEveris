package fbo.costa.bootcampeveris.repository

import fbo.costa.bootcampeveris.util.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.sqrt

class RepositoryChallenge6 {
    fun calculate(list: List<String>): Flow<DataState<String>> = flow {
        try {
            for (i in list.indices) {
                val result = if (isPrime(list[i].toDouble())) "Prime"
                else "Not Prime"

                // Each value emit the result the list can be large
                emit(DataState.Success(result))
            }

        } catch (e: Exception) {
            // If an error occurs send a message
            emit(DataState.Error("Exception: ${e.message}"))
        }
    }

    private fun isPrime(num: Double): Boolean {
        if ((num == 0.0) || (num == 1.0)) return false
        else {
            val end = sqrt(num).toInt()
            for (i in 2..end) {
                if ((num % i) == 0.0) return false
            }
            return true
        }
    }
}
