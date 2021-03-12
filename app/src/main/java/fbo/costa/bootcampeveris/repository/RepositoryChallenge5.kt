package fbo.costa.bootcampeveris.repository

import fbo.costa.bootcampeveris.util.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

class RepositoryChallenge5 {
    fun calculate(radius: String): Flow<DataState<String>> = flow {
        try {
            val pi = 3.14159

            // A = π * radius2.
            val result = BigDecimal(pi * radius.toDouble().pow(2))
                .setScale(4, RoundingMode.HALF_EVEN).toString()

            // If all right Emit the values
            emit(DataState.Success("A=$result"))

        } catch (e: Exception) {
            // If an error occurs send a message
            emit(DataState.Error("Exception: ${e.message}"))
        }
    }
}
