package org.kshrd.cloud.Handler

import org.kshrd.cloud.Service.BookingService
import org.kshrd.cloud.model.DTO.request.BookingRequestDto
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono

@Component
class BookingHandler(private val bookingService: BookingService) {

    fun createBooking(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono<BookingRequestDto>()
            .flatMap { bookingService.createBooking(it) }
            .flatMap { ServerResponse.status(HttpStatus.CREATED).bodyValue(it) }
    }

    fun getBookingById(request: ServerRequest): Mono<ServerResponse> {
        val bookingId = request.pathVariable("bookingId").toLong()
        return bookingService.getBookingById(bookingId)
            .flatMap { ServerResponse.ok().bodyValue(it) }
    }

    fun getAllBookings(request: ServerRequest): Mono<ServerResponse> {
        return bookingService.getAllBookings()
            .collectList()
            .flatMap { ServerResponse.ok().bodyValue(it) }
    }

    fun getBookingsByUserId(request: ServerRequest): Mono<ServerResponse> {
        val userId = request.pathVariable("userId").toLong()
        return bookingService.getBookingsByUserId(userId)
            .collectList()
            .flatMap { ServerResponse.ok().bodyValue(it) }
    }

    fun cancelBooking(request: ServerRequest): Mono<ServerResponse> {
        val bookingId = request.pathVariable("bookingId").toLong()
        return bookingService.cancelBooking(bookingId)
            .flatMap { ServerResponse.ok().bodyValue(it) }
    }
}