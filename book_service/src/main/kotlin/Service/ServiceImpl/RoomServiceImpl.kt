package org.kshrd.cloud.Service.ServiceImpl

import org.kshrd.cloud.model.DTO.request.RoomRequestDto
import org.kshrd.cloud.model.DTO.response.RoomResponseDto
import org.kshrd.cloud.Repositories.RoomRepository
import org.kshrd.cloud.Service.RoomService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import org.kshrd.cloud.Enum.RoomError
import org.kshrd.cloud.model.mapper.toEntity
import org.kshrd.cloud.model.mapper.toResponseDto

@Service
class RoomServiceImpl(private val roomRepository: RoomRepository) : RoomService{

    override fun getRoomById(roomId: Long): Mono<RoomResponseDto> {
        return roomRepository.findById(roomId)
            .switchIfEmpty(Mono.error(RoomError.ROOM_NOT_FOUND.exception(roomId)))
            .map { it.toResponseDto() }
    }

    override fun createRoom(request: RoomRequestDto): Mono<RoomResponseDto> {
        val entity = request.toEntity()
        return roomRepository.save(entity)
            .map { it.toResponseDto() }
    }

}
