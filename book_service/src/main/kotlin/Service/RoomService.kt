package org.kshrd.cloud.Service

import org.kshrd.cloud.model.DTO.request.RoomRequestDto
import org.kshrd.cloud.model.DTO.response.RoomResponseDto
import reactor.core.publisher.Mono

interface RoomService {
    fun getRoomById(roomId: Long): Mono<RoomResponseDto>
    fun createRoom(request: RoomRequestDto): Mono<RoomResponseDto>
}