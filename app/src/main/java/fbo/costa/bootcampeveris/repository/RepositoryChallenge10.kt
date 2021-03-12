package fbo.costa.bootcampeveris.repository

import fbo.costa.bootcampeveris.util.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryChallenge10 {
    fun calculate(input: String): Flow<DataState<String>> = flow {
        try {
            val regex = """0x.*""".toRegex()
            val result: String

            if (regex.containsMatchIn(input)) {
                val decimal = Integer.decode(input)
                result = decimal.toString()

            } else {
                var number = input.toInt()

                // Store the remainder
                var remainder: Int

                // Stores the result
                var hexadecimal = ""

                while (number > 0) {
                    remainder = number % 16
                    hexadecimal = hexArray()[remainder].toString() + hexadecimal
                    number /= 16
                }
                result = "0x$hexadecimal"
            }

            // Each value emit the result the list can be large
            emit(DataState.Success(result))

        } catch (e: Exception) {
            // If an error occurs send a message
            emit(DataState.Error("Exception: ${e.message}"))
        }
    }

    // Hexadecimal number system
    private fun hexArray() = charArrayOf(
        '0',
        '1',
        '2',
        '3',
        '4',
        '5',
        '6',
        '7',
        '8',
        '9',
        'A',
        'B',
        'C',
        'D',
        'E',
        'F'
    )
}
