package org.kshrd.cloud.Handler

import org.kshrd.cloud.model.DTO.request.RoomRequestDto
import org.kshrd.cloud.Service.RoomService
import org.kshrd.cloud.model.DTO.response.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import kotlin.jvm.java

@Component
class RoomHandler(private val roomService: RoomService) {

    fun getAll(request: ServerRequest): Mono<ServerResponse> {
        return roomService.getAll()
            .collectList()
            .map { rooms ->
                ApiResponse(
                    status = "success",
                    message = "Rooms retrieved successfully",
                    data = rooms
                )
            }
            .flatMap { response ->
                ServerResponse.ok()
                    .contentType((MediaType.APPLICATION_JSON))
                    .bodyValue(response)
            }
    }

    fun getRoomById(request: ServerRequest): Mono<ServerResponse> {
        val roomId =  request.pathVariable("id").toLong()
        return roomService.getRoomById(roomId)
            .map { roomId ->
                ApiResponse(
                    status = "success",
                    message = "Rooms retrieved successfully",
                    data = roomId
                )
            }
            .flatMap { room ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(room)
            }
    }

      fun createRoom(request: ServerRequest): Mono<ServerResponse> {
          return request.bodyToMono(RoomRequestDto::class.java)
              .flatMap { room ->
                  roomService.createRoom(room)
              }
              .flatMap { savedRoom ->
                  ServerResponse.status(HttpStatus.CREATED)
                      .contentType(MediaType.APPLICATION_JSON)
                      .bodyValue(savedRoom)
              }
              .onErrorResume { error ->
                  ServerResponse.badRequest()
                      .bodyValue(mapOf("error" to (error.message ?: "Failed to create room")))
              }
      }
}