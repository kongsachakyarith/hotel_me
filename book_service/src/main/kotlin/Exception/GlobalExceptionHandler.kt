package org.kshrd.cloud.Exception

import org.kshrd.cloud.model.DTO.response.ErrorResponse
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.server.ResponseStatusException

@Component
class GlobalErrorAttributes : DefaultErrorAttributes() {

    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): Map<String, Any> {
        val errorAttributes = super.getErrorAttributes(request, options)
        val error = getError(request)


        return when (error) {
            is ResponseStatusException -> {
                val status = HttpStatus.resolve(error.statusCode.value()) ?: HttpStatus.INTERNAL_SERVER_ERROR

                val message = error.reason ?: "An error occurred"
                val errorCode = extractErrorCode(message)

                mapOf(
                    "timestamp" to (errorAttributes["timestamp"] ?: System.currentTimeMillis()),
                    "status" to error.statusCode.value(),
                    "errorCode" to (errorCode?: "UNKNOWN_ERROR"),
                    "error" to status.reasonPhrase, // Use resolved HttpStatus
                    "message" to (error.reason ?: "An error occurred"),
                    "path" to (errorAttributes["path"] ?: request.path())
                )
            }
            is NumberFormatException -> {
                mapOf(
                    "timestamp" to System.currentTimeMillis(),
                    "status" to HttpStatus.BAD_REQUEST.value(),
                    "errorCode" to "ROOM_004",
                    "error" to HttpStatus.BAD_REQUEST.reasonPhrase,
                    "message" to "Invalid ID format",
                    "path" to request.path()
                )
            }
            is IllegalArgumentException -> {
                mapOf(
                    "timestamp" to System.currentTimeMillis(),
                    "status" to HttpStatus.BAD_REQUEST.value(),
                    "error" to HttpStatus.BAD_REQUEST.reasonPhrase,
                    "message" to (error.message ?: "Invalid request"),
                    "path" to request.path()
                )
            }
            else -> {
                mapOf(
                    "timestamp" to System.currentTimeMillis(),
                    "status" to HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "error" to HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
                    "message" to "An unexpected error occurred",
                    "path" to request.path()
                )
            }
        }
    }

    private fun extractErrorCode(message: String): String? {
        val regex = "\\[(\\w+)]".toRegex()
        return regex.find(message)?.groupValues?.get(1)
    }
}