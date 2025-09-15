package org.kshrd.cloud.Enum

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

enum class RoomError(
    val code: String,
    val status: HttpStatus,
    val messageTemplate: String
) {
    ROOM_NOT_FOUND("000010", HttpStatus.NOT_FOUND, "Room with ID %s not found"),
    INVALID_ROOM_DATA("000011", HttpStatus.BAD_REQUEST, "%s"),
    ROOM_ALREADY_EXISTS("000012", HttpStatus.CONFLICT, "Room with number %s already exists"),
    INVALID_ID_FORMAT("000013", HttpStatus.BAD_REQUEST, "Invalid room ID format"),
    MISSING_REQUEST_BODY("000014", HttpStatus.BAD_REQUEST, "Request body is required"),
    ROOM_NUMBER_REQUIRED("000015", HttpStatus.BAD_REQUEST, "Room number is required");

    fun exception(vararg args: Any): ResponseStatusException {
        val message = if (args.isNotEmpty()) {
            messageTemplate.format(*args)
        } else {
            messageTemplate
        }
        return ResponseStatusException(status, "[$code] $message")
    }

    fun toErrorMap(vararg args: Any): Map<String, Any> {
        val message = if (args.isNotEmpty()) {
            messageTemplate.format(*args)
        } else {
            messageTemplate
        }
        return mapOf(
            "errorCode" to code,
            "message" to message,
            "status" to status.value(),
            "error" to status.reasonPhrase
        )
    }
}