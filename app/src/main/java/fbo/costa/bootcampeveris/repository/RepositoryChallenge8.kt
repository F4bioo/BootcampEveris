package fbo.costa.bootcampeveris.repository

import fbo.costa.bootcampeveris.util.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryChallenge8 {
    fun calculate(list: List<String>): Flow<DataState<String>> = flow {
        try {
            val mdc = if (list[0] > list[1]) {
                mdc(list[0].toInt(), list[1].toInt()) // recebe o MDC Maior Divisor Comum
            } else {
                mdc(list[1].toInt(), list[0].toInt()) // recebe o MDC Maior Divisor Comum
            }

            val result = mdc.toString()

            // Each value emit the result the list can be large
            emit(DataState.Success(result))

        } catch (e: Exception) {
            // If an error occurs send a message
            emit(DataState.Error("Exception: ${e.message}"))
        }
    }

    // Maximum Common Divisor (recursive call)
    fun mdc(n1: Int, n2: Int): Int {
        var a = n1
        var b = n2
        var resto: Int

        do {
            resto = a % b // receives the remainder of the division from a to b
            a = b // following Euclid's algorithm, the highest value becomes what was dividing
            b = resto // and what divides is the rest of the division
        } while (resto != 0) // Repeat until you find a case where the division is accurate

        return a // receives the Greatest Common Divisor for the GCF
    }
}
