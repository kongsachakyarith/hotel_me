package org.kshrd.cloud.model.DTO.response

import java.math.BigDecimal
import java.time.LocalDateTime

data class RoomResponseDto(
    val roomId: Long,
    val roomName: String,
    val roomType: String,
    val pricePerNight: BigDecimal,

    )
