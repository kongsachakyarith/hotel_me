package org.kshrd.cloud.Service

import org.kshrd.cloud.model.DTO.request.BookingRequestDto
import org.kshrd.cloud.model.DTO.response.BookingResponseDto
import org.kshrd.cloud.model.mapper.BookingSummaryDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BookingService {
    fun createBooking(request: BookingRequestDto): Mono<BookingResponseDto>
    fun getBookingById(bookingId: Long): Mono<BookingResponseDto>
    fun getAllBookings(): Flux<BookingResponseDto>
    fun getBookingsByUserId(userId: Long): Flux<BookingSummaryDto>
    fun cancelBooking(bookingId: Long): Mono<BookingResponseDto>
}