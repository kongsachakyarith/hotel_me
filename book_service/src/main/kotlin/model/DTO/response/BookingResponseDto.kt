package org.kshrd.cloud.model.DTO.response

import java.time.LocalDate
import java.time.LocalDateTime

data class BookingResponseDto(
    val bookingId: Long,
    val userId: Long,
    val roomId: Long,
    val bookingStatus: String,
    val bookingDate: LocalDateTime,
    val checkInDate: LocalDate,
    val checkOutDate: LocalDate
)