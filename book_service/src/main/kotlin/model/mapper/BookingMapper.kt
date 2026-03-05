package org.kshrd.cloud.model.mapper

import org.kshrd.cloud.model.DTO.request.BookingRequestDto
import org.kshrd.cloud.model.DTO.response.BookingResponseDto
import org.kshrd.cloud.model.Entity.Booking
import org.kshrd.cloud.model.Entity.Room
import java.time.LocalDate
import kotlin.Long

fun BookingRequestDto.toEntity(room: Room) : Booking {

    return Booking(
        userId = this.userId,
        room = room,
        checkInDate = this.checkInDate,
        checkOutDate = this.checkOutDate
    )
}

fun Booking.toResponseDto(): BookingResponseDto {
    return BookingResponseDto(
        bookingId = this.bookingId,
        userId = this.userId,
        roomId = this.room.roomId,   // assume Room has id field
        bookingStatus = this.bookingStatus,
        bookingDate = this.bookingDate,
        checkInDate = this.checkInDate,
        checkOutDate = this.checkOutDate
    )
}

data class BookingSummaryDto(
    val bookingId: Long,
    val roomId: Long,
    val bookingStatus: String,
    val checkInDate: LocalDate,
    val checkOutDate: LocalDate
)

fun Booking.toSummaryDto(): BookingSummaryDto {
    return BookingSummaryDto(
        bookingId = this.bookingId,
        roomId = this.room.roomId,
        bookingStatus = this.bookingStatus,
        checkInDate = this.checkInDate,
        checkOutDate = this.checkOutDate
    )
}