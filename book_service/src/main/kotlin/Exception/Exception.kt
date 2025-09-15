package org.kshrd.cloud.Exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class RoomNotFoundException(id: Long) :
    ResponseStatusException(HttpStatus.NOT_FOUND, "Room with ID $id not found")

class InvalidRoomDataException(message: String) :
    ResponseStatusException(HttpStatus.BAD_REQUEST, message)

class RoomAlreadyExistsException(roomNumber: String) :
    ResponseStatusException(HttpStatus.CONFLICT, "Room with number $roomNumber already exists")