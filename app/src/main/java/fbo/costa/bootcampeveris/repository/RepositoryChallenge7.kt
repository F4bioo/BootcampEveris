package fbo.costa.bootcampeveris.repository

import fbo.costa.bootcampeveris.util.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryChallenge7 {
    fun calculate(list: List<Int>): Flow<DataState<String>> = flow {
        try {
            var level = 0
            var result = ""

            for (i in list.indices) {
                val velocity = list[i]
                if (velocity in 1..50) {
                    if (level < velocity) level = velocity
                }
            }

            when {
                // Leve 1: If the speed is less than 10 cm/h
                level < 10 -> result = "1"

                // Level 2: If the speed is greater than or equal to 10 cm/h and less than 20 cm/h
                level in 10..19 -> result = "2"

                // Level 3: If the speed is greater than or equal to 20 cm/h
                level >= 20 -> result = "3"
            }

            // Each value emit the result the list can be large
            emit(DataState.Success(result))

        } catch (e: Exception) {
            // If an error occurs send a message
            emit(DataState.Error("Exception: ${e.message}"))
        }
    }
}
