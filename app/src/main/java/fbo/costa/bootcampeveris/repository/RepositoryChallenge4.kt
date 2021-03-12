package fbo.costa.bootcampeveris.repository

import fbo.costa.bootcampeveris.util.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryChallenge4 {
    fun calculate(salary: String): Flow<DataState<String>> = flow {
        try {
            val result = calculate(salary.toFloat())

            // If all right Emit the values
            emit(DataState.Success(result))

        } catch (e: Exception) {
            // If an error occurs send a message
            emit(DataState.Error("Exception: ${e.message}"))
        }
    }

    private fun calculate(salary: Float): String {
        var i = 0F

        when {
            salary in 0.00..2000.00 -> i = 0F

            salary in 2000.01..3000.00 -> { // 8%
                val aux = salary - 2000
                val tax8 = aux.times(8.0F / 100)

                i = tax8
            }
            salary in 3000.01..4500.00 -> { // 18%
                val aux = salary - 3000
                val tax8 = 1000.times(8.0F / 100)
                val tax18 = aux.times(18.0F / 100)

                i = (tax8 + tax18)
            }
            salary > 4500 -> { // 28%
                val aux = salary - 4500
                val tax8 = 1000.times(8.0F / 100)
                val tax18 = 1500.times(18.0F / 100)
                val tax28 = aux.times(28.0F / 100)

                i = (tax8 + tax18 + tax28)
            }
        }

        return if (i == 0F) "Isento"
        else "R$ ${i.format(2)}"
    }

    private fun Float.format(digits: Int) =
        "%.${digits}f".format(this).replace(',', '.')
}
