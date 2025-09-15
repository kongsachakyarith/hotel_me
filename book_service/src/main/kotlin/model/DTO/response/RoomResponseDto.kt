package org.kshrd.cloud.model.DTO.response

import java.math.BigDecimal

data class RoomResponseDto(
    val roomId: Long,
    val roomName: String,
    val roomType: String,
    val pricePerNight: BigDecimal,

    )

data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data: T?
)
