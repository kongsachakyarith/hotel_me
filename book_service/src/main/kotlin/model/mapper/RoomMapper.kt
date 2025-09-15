package org.kshrd.cloud.model.mapper

import org.kshrd.cloud.model.DTO.request.RoomRequestDto
import org.kshrd.cloud.model.DTO.response.RoomResponseDto
import org.kshrd.cloud.model.Entity.Room
import java.math.BigDecimal


fun RoomRequestDto.toEntity(): Room {
    return Room(
        roomName = this.roomName,
        roomType = this.roomType,
        pricePerNight = this.pricePerNight,
    )
}

fun Room.toResponseDto(): RoomResponseDto {
    return RoomResponseDto(
        roomId = this.roomId,
        roomName = this.roomName,
        roomType = this.roomType,
        pricePerNight = this.pricePerNight,
    )
}

data class RoomSummaryDto(
    val roomId: Long,
    val roomName: String,
    val roomType: String,
    val pricePerNight: BigDecimal
)

fun Room.toSummaryDto(): RoomSummaryDto {
    return RoomSummaryDto(
        roomId = this.roomId,
        roomName = this.roomName,
        roomType = this.roomType,
        pricePerNight = this.pricePerNight
    )
}