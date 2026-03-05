package org.kshrd.cloud.service.impl

import org.kshrd.cloud.Enum.RoomError
import org.kshrd.cloud.Repositories.BookingRepository
import org.kshrd.cloud.Repositories.RoomRepository
import org.kshrd.cloud.Service.BookingService
import org.kshrd.cloud.model.DTO.request.BookingRequestDto
import org.kshrd.cloud.model.DTO.response.BookingResponseDto
import org.kshrd.cloud.model.mapper.BookingSummaryDto
import org.kshrd.cloud.model.mapper.toEntity
import org.kshrd.cloud.model.mapper.toResponseDto
import org.kshrd.cloud.model.mapper.toSummaryDto
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BookingServiceImpl(

    private val bookingRepository: BookingRepository,
    private val  roomRepository: RoomRepository

) : BookingService {
    override fun createBooking(request: BookingRequestDto): Mono<BookingResponseDto> {

        return roomRepository.findById(request.roomId)
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "Room is Not Found")))
            .flatMap { room ->                        // room is Room type ✅
                val booking = request.toEntity(room)
                bookingRepository.save(booking)
            }
            .map { it.toResponseDto() }

    }

    override fun getBookingById(bookingId: Long): Mono<BookingResponseDto> {

        return bookingRepository.findById(bookingId)
            .switchIfEmpty(Mono.error(RoomError.ROOM_NOT_FOUND.exception(bookingId)))
            .map { it.toResponseDto() }
    }

    override fun getAllBookings(): Flux<BookingResponseDto> {
        return bookingRepository.findAll().map {
            it.toResponseDto()
        }
    }

    override fun getBookingsByUserId(userId: Long): Flux<BookingSummaryDto> {
        return bookingRepository.findByUserId(userId).map { it.toSummaryDto() }
    }

    override fun cancelBooking(bookingId: Long): Mono<BookingResponseDto> {

        return bookingRepository.findById(bookingId)
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "Room is Nt Found")))
            .flatMap { booking  ->
                val cancel = booking.copy(bookingStatus = "CANCELLED" )
                bookingRepository.save(cancel)
            }.map { it.toResponseDto() }
    }


}