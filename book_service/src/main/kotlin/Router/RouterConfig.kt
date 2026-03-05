package org.kshrd.cloud.Router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.kshrd.cloud.Handler.RoomHandler
import org.kshrd.cloud.Handler.BookingHandler
import org.kshrd.cloud.model.DTO.request.BookingRequestDto
import org.kshrd.cloud.model.DTO.request.RoomRequestDto
import org.kshrd.cloud.model.DTO.response.BookingResponseDto
import org.kshrd.cloud.model.DTO.response.ErrorResponse
import org.kshrd.cloud.model.DTO.response.RoomResponseDto
import org.kshrd.cloud.model.mapper.BookingSummaryDto
import org.kshrd.cloud.model.mapper.RoomSummaryDto
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
open class RouterConfig {

    @Bean
    @RouterOperations(
        RouterOperation(
            path = "/api/v1/rooms",
            method = [RequestMethod.POST],
            beanClass = RoomHandler::class,
            beanMethod = "createRoom",
            operation = Operation(
                operationId = "createRoom",
                tags = ["Room Management"],
                summary = "Create a new room",
                description = "Creates a new room with the provided details",
                requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Room details",
                    required = true,
                    content = [Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = RoomRequestDto::class)
                    )]
                ),
                responses = [
                    ApiResponse(
                        responseCode = "201",
                        description = "Room created successfully",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = RoomResponseDto::class)
                        )]
                    ),
                    ApiResponse(
                        responseCode = "400",
                        description = "Invalid request data",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = ErrorResponse::class)
                        )]
                    ),
                    ApiResponse(
                        responseCode = "409",
                        description = "Room already exists",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = ErrorResponse::class)
                        )]
                    )
                ]
            )
        ),
        RouterOperation(
            path = "/api/v1/rooms/{id}",
            method = [RequestMethod.GET],
            beanClass = RoomHandler::class,
            beanMethod = "getRoomById",
            operation = Operation(
                operationId = "getRoomById",
                tags = ["Room Management"],
                summary = "Get room by ID",
                description = "Retrieves a room by its unique identifier",
                parameters = [
                    Parameter(
                        name = "id",
                        description = "Room ID",
                        required = true,
                        `in` = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
                        schema = Schema(type = "integer", format = "int64", example = "1")
                    )
                ],
                responses = [
                    ApiResponse(
                        responseCode = "200",
                        description = "Room found",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = RoomResponseDto::class)
                        )]
                    ),
                    ApiResponse(
                        responseCode = "404",
                        description = "Room not found",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = ErrorResponse::class)
                        )]
                    )
                ]
            )
        ),
        RouterOperation(
            path = "/api/v1/rooms",
            method = [RequestMethod.GET],
            beanClass = RoomHandler::class,
            beanMethod = "getAll",
            operation = Operation(
                operationId = "getAllRooms",
                tags = ["Room Management"],
                summary = "Get all rooms",
                description = "Retrieves a list of all rooms (summary view)",
                responses = [
                    ApiResponse(
                        responseCode = "200",
                        description = "Rooms retrieved successfully",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(type = "array", implementation = RoomSummaryDto::class)
                        )]
                    )
                ]
            )
        )
    )
    fun roomRoutes(roomHandler: RoomHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .path("/api/v1/rooms") { builder ->
                builder
                    .GET("/{id}", roomHandler::getRoomById)
                    .GET("", roomHandler::getAll)
                    .POST("", roomHandler::createRoom)
            }
            .build()
    }

    @Bean
    @RouterOperations(
        RouterOperation(
            path = "/api/v1/bookings",
            method = [RequestMethod.POST],
            beanClass = BookingHandler::class,
            beanMethod = "createBooking",
            operation = Operation(
                operationId = "createBooking",
                tags = ["Booking Management"],
                summary = "Create a new booking",
                description = "Creates a new booking with the provided details",
                requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Booking details",
                    required = true,
                    content = [Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = BookingRequestDto::class)
                    )]
                ),
                responses = [
                    ApiResponse(
                        responseCode = "201",
                        description = "Booking created successfully",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = BookingResponseDto::class)
                        )]
                    ),
                    ApiResponse(
                        responseCode = "400",
                        description = "Invalid request data",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = ErrorResponse::class)
                        )]
                    ),
                    ApiResponse(
                        responseCode = "404",
                        description = "Room not found",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = ErrorResponse::class)
                        )]
                    )
                ]
            )
        ),
        RouterOperation(
            path = "/api/v1/bookings",
            method = [RequestMethod.GET],
            beanClass = BookingHandler::class,
            beanMethod = "getAllBookings",
            operation = Operation(
                operationId = "getAllBookings",
                tags = ["Booking Management"],
                summary = "Get all bookings",
                description = "Retrieves a list of all bookings",
                responses = [
                    ApiResponse(
                        responseCode = "200",
                        description = "Bookings retrieved successfully",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(type = "array", implementation = BookingResponseDto::class)
                        )]
                    )
                ]
            )
        ),
        RouterOperation(
            path = "/api/v1/bookings/{bookingId}",
            method = [RequestMethod.GET],
            beanClass = BookingHandler::class,
            beanMethod = "getBookingById",
            operation = Operation(
                operationId = "getBookingById",
                tags = ["Booking Management"],
                summary = "Get booking by ID",
                description = "Retrieves a booking by its unique identifier",
                parameters = [
                    Parameter(
                        name = "bookingId",
                        description = "Booking ID",
                        required = true,
                        `in` = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
                        schema = Schema(type = "integer", format = "int64", example = "1")
                    )
                ],
                responses = [
                    ApiResponse(
                        responseCode = "200",
                        description = "Booking found",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = BookingResponseDto::class)
                        )]
                    ),
                    ApiResponse(
                        responseCode = "404",
                        description = "Booking not found",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = ErrorResponse::class)
                        )]
                    )
                ]
            )
        ),
        RouterOperation(
            path = "/api/v1/bookings/user/{userId}",
            method = [RequestMethod.GET],
            beanClass = BookingHandler::class,
            beanMethod = "getBookingsByUserId",
            operation = Operation(
                operationId = "getBookingsByUserId",
                tags = ["Booking Management"],
                summary = "Get bookings by user ID",
                description = "Retrieves all bookings for a specific user",
                parameters = [
                    Parameter(
                        name = "userId",
                        description = "User ID",
                        required = true,
                        `in` = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
                        schema = Schema(type = "integer", format = "int64", example = "1")
                    )
                ],
                responses = [
                    ApiResponse(
                        responseCode = "200",
                        description = "Bookings retrieved successfully",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(type = "array", implementation = BookingSummaryDto::class)
                        )]
                    )
                ]
            )
        ),
        RouterOperation(
            path = "/api/v1/bookings/{bookingId}/cancel",
            method = [RequestMethod.PUT],
            beanClass = BookingHandler::class,
            beanMethod = "cancelBooking",
            operation = Operation(
                operationId = "cancelBooking",
                tags = ["Booking Management"],
                summary = "Cancel a booking",
                description = "Cancels an existing booking by its ID",
                parameters = [
                    Parameter(
                        name = "bookingId",
                        description = "Booking ID",
                        required = true,
                        `in` = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH,
                        schema = Schema(type = "integer", format = "int64", example = "1")
                    )
                ],
                responses = [
                    ApiResponse(
                        responseCode = "200",
                        description = "Booking cancelled successfully",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = BookingResponseDto::class)
                        )]
                    ),
                    ApiResponse(
                        responseCode = "404",
                        description = "Booking not found",
                        content = [Content(
                            mediaType = "application/json",
                            schema = Schema(implementation = ErrorResponse::class)
                        )]
                    )
                ]
            )
        )
    )
    fun bookingRoutes(bookingHandler: BookingHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .path("/api/v1/bookings") { builder ->
                builder
                    .GET("", bookingHandler::getAllBookings)
                    .GET("/{bookingId}", bookingHandler::getBookingById)
                    .GET("/user/{userId}", bookingHandler::getBookingsByUserId)
                    .POST("", bookingHandler::createBooking)
                    .PUT("/{bookingId}/cancel", bookingHandler::cancelBooking)
            }
            .build()
    }
}