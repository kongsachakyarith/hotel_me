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

    /*
      fun updateRoom(request: ServerRequest): Mono<ServerResponse> {
          return try {
              val roomId = request.pathVariable("id").toLong()
              request.bodyToMono(Room::class.java)
                  .flatMap { updatedRoom ->
                      roomRepository.findById(roomId)
                          .flatMap { existingRoom ->
                              val roomToUpdate = existingRoom.copy(
                                  roomName = updatedRoom.roomName,
                                  roomType = updatedRoom.roomType,
                                  pricePerNight = updatedRoom.pricePerNight,
                                  availabilityStatus = updatedRoom.availabilityStatus
                              )
                              roomRepository.save(roomToUpdate)
                          }
                  }
                  .flatMap { savedRoom ->
                      ServerResponse.ok()
                          .contentType(MediaType.APPLICATION_JSON)
                          .bodyValue(savedRoom)
                  }
                  .switchIfEmpty(ServerResponse.notFound().build())
          } catch (e: NumberFormatException) {
              ServerResponse.badRequest()
                  .bodyValue(mapOf("error" to "Invalid room ID format"))
          }
      }

      fun deleteRoom(request: ServerRequest): Mono<ServerResponse> {
          return try {
              val roomId = request.pathVariable("id").toLong()
              roomRepository.findById(roomId)
                  .flatMap { room ->
                      roomRepository.deleteById(roomId)
                          .then(ServerResponse.noContent().build())
                  }
                  .switchIfEmpty(ServerResponse.notFound().build())
          } catch (e: NumberFormatException) {
              ServerResponse.badRequest()
                  .bodyValue(mapOf("error" to "Invalid room ID format"))
          }
      }

      // Search rooms by availability status
      fun getRoomsByStatus(request: ServerRequest): Mono<ServerResponse> {
          val status = request.queryParam("status").orElse("AVAILABLE")
          return ServerResponse.ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(roomRepository.findByAvailabilityStatus(status), Room::class.java)
      }

      // Search rooms by type
      fun getRoomsByType(request: ServerRequest): Mono<ServerResponse> {
          val roomType = request.queryParam("type").orElse("")
          return if (roomType.isNotEmpty()) {
              ServerResponse.ok()
                  .contentType(MediaType.APPLICATION_JSON)
                  .body(roomRepository.findByRoomType(roomType), Room::class.java)
          } else {
              ServerResponse.badRequest()
                  .bodyValue(mapOf("error" to "Room type parameter is required"))
          }
      }

      // Search rooms by price range
      fun getRoomsByPriceRange(request: ServerRequest): Mono<ServerResponse> {
          val minPrice = request.queryParam("minPrice").map { it.toDouble() }.orElse(0.0)
          val maxPrice = request.queryParam("maxPrice").map { it.toDouble() }.orElse(Double.MAX_VALUE)

          return ServerResponse.ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(roomRepository.findByPricePerNightBetween(minPrice, maxPrice), Room::class.java)
      }*/
}